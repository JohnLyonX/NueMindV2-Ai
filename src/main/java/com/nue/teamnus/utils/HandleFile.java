package com.nue.teamnus.utils;

import com.nue.teamnus.service.IAgentService;
import org.apache.poi.xwpf.usermodel.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openxmlformats.schemas.drawingml.x2006.main.CTPositiveSize2D;
import org.openxmlformats.schemas.drawingml.x2006.picture.CTPicture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class HandleFile {

    private List<ImageDataExtended> imageDataList = new ArrayList<>();

    @Autowired
    private DocImageExtraction docImageExtraction;


    // 处理文本内容
    public String parseWordFile(InputStream is) throws Exception {
        StringBuilder content = new StringBuilder();
        int imageIndex = 1;

        try (XWPFDocument doc = new XWPFDocument(is)) {
            for (IBodyElement elem : doc.getBodyElements()) {
                if (!(elem instanceof XWPFParagraph)) {
                    continue;
                }
                XWPFParagraph para = (XWPFParagraph) elem;
                StringBuilder paraText = new StringBuilder();

                List<XWPFRun> runs = para.getRuns();
                for (int i = 0; i < runs.size(); i++) {
                    XWPFRun run = runs.get(i);

                    // 提取文本
                    String text = run.getText(0);
                    if (text != null) {
                        text = text.replace("\u000B", "\n")
                                .replace("\r", "\n");
                        paraText.append(text);
                    }

                    // 提取并处理图片
                    for (XWPFPicture picture : run.getEmbeddedPictures()) {
                        CTPicture ctPic = picture.getCTPicture();
                        CTPositiveSize2D ext = ctPic.getSpPr().getXfrm().getExt();
                        int width = (int) ext.getCx();
                        int height = (int) ext.getCy();

                        byte[] imageData = picture.getPictureData().getData();
                        String fileName = "img_" + imageIndex + ".png";
                        String placeholder = "[IMAGE_" + imageIndex + "]";

                        // 插入占位符
                        paraText.append(placeholder).append("\n");

                        // 调用 AI OCR
                        MultipartFile multipartFile = new ByteArrayMultipartFile(
                                fileName, fileName,
                                picture.getPictureData().getPackagePart().getContentType(),
                                imageData
                        );
                        String ocrText = docImageExtraction.handleDocImg(multipartFile);

                        // 插入 OCR 结果
                        paraText.append("[作答: " + ocrText + " ]").append("\n");

                        // 保存图片数据（包含 OCR）
                        imageDataList.add(new ImageDataExtended(
                                placeholder, imageData, width, height
                        ));

                        imageIndex++;
                    }

                    // 判断是否需要人工换行（根据选项）
                    if (i < runs.size() - 1) {
                        XWPFRun nextRun = runs.get(i + 1);
                        String nextText = nextRun.getText(0);
                        if (nextText != null && nextText.matches("^[A-D]\\..*")) {
                            paraText.append("\n");
                        }
                    }
                }

                content.append(paraText).append("\n");
            }
        }

        return content.toString();
    }


    // 扩展后的内部类：包含原始图片宽高
    private static class ImageDataExtended {
        String placeholder;
        byte[] data;
        int width;  // 宽度 (EMU)
        int height; // 高度 (EMU)

        public ImageDataExtended(String placeholder, byte[] data, int width, int height) {
            this.placeholder = placeholder;
            this.data = data;
            this.width = width;
            this.height = height;
        }
    }

    // 分数统计方法
    public int calculateTotalScore(String processedContent) {
        int totalScore = 0;
        Pattern pattern = Pattern.compile("\\[正确：(\\d+)\\]"); // 修改后的正则表达式
        Matcher matcher = pattern.matcher(processedContent);

        while (matcher.find()) {
            try {
                int score = Integer.parseInt(matcher.group(1));
                totalScore += score;
            } catch (NumberFormatException e) {
                System.err.println("无效的分数格式: " + matcher.group(1));
            }
        }
        return totalScore;
    }


    public void writeToWordFile(String processedContent, String outputPath) throws Exception {
        try (XWPFDocument doc = new XWPFDocument()) {
            int imageCounter = 1;
            String[] paragraphs = processedContent.split("\n");

            for (String paraText : paragraphs) {
                XWPFParagraph para = doc.createParagraph();
                XWPFRun run = para.createRun();

                // 使用正则表达式匹配占位符
                java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("\\[IMAGE_\\d+\\]");
                java.util.regex.Matcher matcher = pattern.matcher(paraText);

                int lastIndex = 0;
                while (matcher.find()) {
                    // 添加前置文本
                    String textBefore = paraText.substring(lastIndex, matcher.start());
                    if (!textBefore.isEmpty()) {
                        run.setText(textBefore);
                        run = para.createRun(); // 创建新的run保持格式
                    }

                    // 处理图片
                    String placeholder = matcher.group();
                    ImageDataExtended imageData = imageDataList.stream().filter(data -> data.placeholder.equals(placeholder)).findFirst().orElseThrow(() -> new IllegalStateException("图片占位符缺失数据: " + placeholder));

                    // 使用图片原始尺寸（单位：EMU）
                    run.addPicture(new ByteArrayInputStream(imageData.data), XWPFDocument.PICTURE_TYPE_PNG, "image_" + imageCounter + ".png", imageData.width, imageData.height);
                    imageCounter++;

                    lastIndex = matcher.end();
                    run = para.createRun(); // 为后续文本创建新的run
                }

                // 添加剩余文本
                String remainingText = paraText.substring(lastIndex);
                if (!remainingText.isEmpty()) {
                    run.setText(remainingText);
                }
            }

            try (FileOutputStream out = new FileOutputStream(outputPath)) {
                doc.write(out);
            }
        }
    }

     public String removeProfileUploadPrefix(String originalPath) {
        int profileIndex = originalPath.indexOf("/profile");
        return originalPath.substring(profileIndex + "/profile".length());
    }

    public String removeFileNameFromPath(String filePath) {
        int lastIndex = filePath.lastIndexOf('/');
        if (lastIndex != -1) {
            return filePath.substring(0, lastIndex + 1);
        }
        return "";
    }



}
