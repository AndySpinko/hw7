package com.github.javarar.animal.faces;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AnimalFacesPipeline {

    private static final ExecutorService executorService = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {

        AnimalFacesPipeline animalFacesPipeline = new AnimalFacesPipeline();
        animalFacesPipeline.editImage(executorService);
    }

    public void editImage(ExecutorService executorService) {
        File[] list = new File("task-2-animal-faces/src/main/resources/images").listFiles();
        for (File file : list) {
            executorService.submit(() -> {
                try {
                    var image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
                    var filledImage = image.createGraphics();
                    filledImage.setPaint(new Color(0, 0, 255));
                    filledImage.fillRect(0, 0, image.getWidth(), image.getHeight());
                    var fileName = "task-2-animal-faces/src/main/resources/result/blue_" + file.getName();
                    ImageIO.write(image, "jpg", new File(fileName));
                } catch (Exception exception) {
                }
            });
        }
        executorService.shutdown();
    }
}
