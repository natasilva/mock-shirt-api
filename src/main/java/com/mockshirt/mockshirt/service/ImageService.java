package com.mockshirt.mockshirt.service;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Color;

import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Blob;
import javax.imageio.ImageIO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.mockshirt.mockshirt.service.interfaces.IImageService;

@Service
public class ImageService implements IImageService {
    // Carrega a imagem de dentro da pasta images/
    public BufferedImage loadImage(String fileName) throws IOException {
        try (InputStream imgStream = getClass().getClassLoader().getResourceAsStream("images/" + fileName)) {
            if (imgStream == null) {
                throw new IOException("File not found: " + fileName);
            }
            return ImageIO.read(imgStream);
        }
    }

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

    // Converte imagem do tipo MultipartFile para o tipo BufferdImage
    public BufferedImage convertToBufferedImage(MultipartFile multipartFile) {
        try {
            byte[] bytes = multipartFile.getBytes();
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
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
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
    public void rotateAndDrawLogo(Graphics2D g2d, BufferedImage logo, int x, int y, int ang) {
        double radians = Math.toRadians(ang);
        AffineTransform rotation = AffineTransform.getRotateInstance(radians, x + logo.getWidth() / 2,
                y + logo.getHeight() / 2);
        g2d.setTransform(rotation);

        drawLogo(g2d, logo, x, y);
    }

    // Desenha a imagem da logo no contexto
    public void drawLogo(Graphics2D g2d, BufferedImage logo, int x, int y) {
        g2d.drawImage(logo, x, y, null);
    }

    public BufferedImage colorizeImage(BufferedImage image, String hexColor) {
        int width = image.getWidth();
        int height = image.getHeight();

        Color newColor = Color.decode(hexColor);

        // Criar uma nova imagem com o mesmo tamanho da original
        BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        // Percorrer todos os pixels da imagem original
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                // Obter a cor do pixel atual
                Color originalColor = new Color(image.getRGB(x, y), true);

                // Verificar se o pixel é branco ou próximo de branco
                if (isWhiteOrNearWhite(originalColor)) {
                    // Substituir pelo novo pixel colorido
                    newImage.setRGB(x, y, newColor.getRGB());
                } else {
                    // Manter a cor original
                    newImage.setRGB(x, y, originalColor.getRGB());
                }
            }
        }
        return newImage;
    }

    private boolean isWhiteOrNearWhite(Color color) {
        // Definir um limiar para considerar um pixel como "próximo de branco"
        int threshold = 240;
        return color.getRed() >= threshold && color.getGreen() >= threshold && color.getBlue() >= threshold;
    }
}
