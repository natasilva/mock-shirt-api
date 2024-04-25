package com.mockshirt.mockshirt.service;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Blob;
import java.io.File;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Service;

@Service
public class ImageService {

    // Baixa imagem de uma URL
    public BufferedImage downloadImage(String imageUrl) throws IOException, URISyntaxException {
        URI uri = new URI(imageUrl);
        URL url = uri.toURL();
        return ImageIO.read(url);
    }

    // Redimensiona uma imagem
    public BufferedImage scaleImage(BufferedImage image, int width, int height) {
        Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resizedImage.createGraphics();
        g2d.drawImage(scaledImage, 0, 0, null);
        g2d.dispose();
        return resizedImage;
    }

    // Salva uma imagem no diretório
    public void saveImage(BufferedImage image, String filePath) throws IOException {
        ImageIO.write(image, "PNG", new File(filePath));
    }

    // Converte imagem do tipo byte[] para o tipo BufferdImage
    public BufferedImage convertToBufferedImage(byte[] bytes) {
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
            BufferedImage image = ImageIO.read(bis);
            bis.close();
            return image;
        } catch (Exception e) {
            System.out.println("Erro ao converter BufferedImage para Blob: " + e.getMessage());
            return null;
        }
    }

    // Converte uma imagem do tipo BufferdImage para o tipo Blob
    public Blob convertToBlob(BufferedImage image) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, "png", baos);
            byte[] bytes = baos.toByteArray();
            return new javax.sql.rowset.serial.SerialBlob(bytes);
        } catch (Exception e) {
            System.out.println("Erro ao converter BufferedImage para Blob: " + e.getMessage());
            return null;
        }
    }

    // Cria uma imagem em branco com as dimensões fornecidas
    public BufferedImage createBlankImage(int width, int height) {
        return new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    }

    // Desenha a imagem base da camiseta no contexto
    public void drawBaseImage(Graphics2D g2d, BufferedImage baseImage) {
        g2d.drawImage(baseImage, 0, 0, null);
    }

    // Rotaciona a imagem da logo e desenha ela na imagem da camiseta
    public void rotateAndDrawLogo(Graphics2D g2d, BufferedImage logo, int x, int y) {
        double radians = Math.toRadians(45);
        AffineTransform rotation = AffineTransform.getRotateInstance(radians, x + logo.getWidth() / 2,
                y + logo.getHeight() / 2);
        g2d.setTransform(rotation);

        drawLogo(g2d, logo, x, y);
    }

    // Desenha a imagem da logo no contexto
    public void drawLogo(Graphics2D g2d, BufferedImage logo, int x, int y) {
        g2d.drawImage(logo, x, y, null);
    }
}
