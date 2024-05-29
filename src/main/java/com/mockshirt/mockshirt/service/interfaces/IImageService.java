package com.mockshirt.mockshirt.service.interfaces;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Blob;

import org.springframework.web.multipart.MultipartFile;

public interface IImageService {
    BufferedImage loadImage(String filePath) throws IOException;

    BufferedImage downloadImage(String imageUrl) throws IOException, URISyntaxException;

    BufferedImage scaleImage(BufferedImage image, int width, int height);

    BufferedImage convertToBufferedImage(MultipartFile multipartFile);

    Blob convertToBlob(BufferedImage image);

    BufferedImage createBlankImage(int width, int height);

    void drawBaseImage(Graphics2D g2d, BufferedImage baseImage);

    void rotateAndDrawLogo(Graphics2D g2d, BufferedImage logo, int x, int y, int ang);

    void drawLogo(Graphics2D g2d, BufferedImage logo, int x, int y);
}
