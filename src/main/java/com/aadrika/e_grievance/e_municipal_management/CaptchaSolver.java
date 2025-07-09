package com.aadrika.e_grievance.e_municipal_management;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.OutputType;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import org.apache.commons.io.FileUtils;

/**
 * CaptchaSolver class handles CAPTCHA image recognition using Tesseract OCR
 * Provides functionality to capture, preprocess, and solve CAPTCHA images
 */
public class CaptchaSolver {

    // WebDriver instance for browser interactions
    private WebDriver driver;

    /**
     * Constructor to initialize CaptchaSolver with WebDriver
     * @param driver WebDriver instance for browser operations
     */
    public CaptchaSolver(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Main method to solve CAPTCHA using OCR technology
     * Captures CAPTCHA image, preprocesses it, and uses Tesseract OCR for text recognition
     * @return Solved CAPTCHA text as String, null if solving fails
     */
    public String solveCaptcha() {
        try {
            // Locate the CAPTCHA image element on the webpage using CSS selector
            WebElement captchaImage = driver.findElement(By.cssSelector(
                    "#radix-\\:rt\\: > div > form > div.gap-x-2.gap-y-4.px-10.pt-2.pb-10 > div > div.mt-16.flex.flex-col.flex-wrap.gap-0.bg-center > div > div.rounded-sm.px-4.py-1.bg-cover > img"));

            // Capture screenshot of the CAPTCHA image element
            File srcFile = captchaImage.getScreenshotAs(OutputType.FILE);
            File captchaFile = new File("captcha.png");
            FileUtils.copyFile(srcFile, captchaFile);

            // Load and preprocess the captured image for better OCR accuracy
            BufferedImage bufferedImage = ImageIO.read(captchaFile);
            BufferedImage preprocessedImage = preprocessImage(bufferedImage);

            // Save preprocessed image for debugging purposes
            File processedFile = new File("processed_captcha.png");
            ImageIO.write(preprocessedImage, "png", processedFile);

            // Initialize Tesseract OCR engine
            ITesseract instance = new Tesseract();
            // Set path to Tesseract training data (update path as needed)
            instance.setDatapath("C:/Program Files/Tesseract-OCR/tessdata");
            // Set OCR language to English
            instance.setLanguage("eng");

            // Configure OCR settings for CAPTCHA recognition
            // Whitelist only alphanumeric characters
            instance.setTessVariable("tessedit_char_whitelist", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789");
            // Set page segmentation mode for single text line
            instance.setTessVariable("tessedit_pageseg_mode", "7");

            // Perform OCR on the preprocessed image and get text
            String captchaText = instance.doOCR(preprocessedImage).trim();

            // Log and return the solved CAPTCHA text
            System.out.println("Solved CAPTCHA: " + captchaText);
            return captchaText;

        } catch (Exception e) {
            // Log error details for debugging and troubleshooting
            System.err.println("Error solving CAPTCHA: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Preprocesses the CAPTCHA image to enhance OCR accuracy
     * Applies grayscale conversion, scaling, and quality improvements
     * @param originalImage The original BufferedImage captured from CAPTCHA
     * @return Preprocessed BufferedImage optimized for OCR
     */
    private BufferedImage preprocessImage(BufferedImage originalImage) {
        try {
            // Convert original image to grayscale for better text contrast
            BufferedImage grayscaleImage = new BufferedImage(
                    originalImage.getWidth(),
                    originalImage.getHeight(),
                    BufferedImage.TYPE_BYTE_GRAY
            );
            Graphics2D g2d = grayscaleImage.createGraphics();
            g2d.drawImage(originalImage, 0, 0, null);
            g2d.dispose();

            // Scale up the image by 2x to improve OCR text recognition
            int scaledWidth = grayscaleImage.getWidth() * 2;
            int scaledHeight = grayscaleImage.getHeight() * 2;
            BufferedImage scaledImage = new BufferedImage(scaledWidth, scaledHeight, BufferedImage.TYPE_BYTE_GRAY);
            Graphics2D g2 = scaledImage.createGraphics();

            // Apply high-quality rendering hints for better image quality
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Draw scaled image with applied rendering hints
            g2.drawImage(grayscaleImage, 0, 0, scaledWidth, scaledHeight, null);
            g2.dispose();

            // Return the enhanced image ready for OCR processing
            return scaledImage;

        } catch (Exception e) {
            // Log preprocessing errors and return original image as fallback
            System.err.println("Error preprocessing CAPTCHA image: " + e.getMessage());
            e.printStackTrace();
            return originalImage;
        }
    }
}
