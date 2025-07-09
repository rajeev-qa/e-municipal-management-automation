package com.aadrika.egovernance.utils.helpers;

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Helper class for solving CAPTCHA using OCR
 */
public class CaptchaSolver {
    
    private static final Logger logger = LoggerFactory.getLogger(CaptchaSolver.class);
    private final WebDriver driver;
    
    /**
     * Constructor for CaptchaSolver
     * @param driver WebDriver instance
     */
    public CaptchaSolver(WebDriver driver) {
        this.driver = driver;
    }
    
    /**
     * Solves CAPTCHA using OCR
     * @return Solved CAPTCHA text
     */
    public String solveCaptcha() {
        try {
            // Locate CAPTCHA image
            WebElement captchaImage = driver.findElement(By.cssSelector(
                    "#radix-\\:rt\\: > div > form > div.gap-x-2.gap-y-4.px-10.pt-2.pb-10 > div > div.mt-16.flex.flex-col.flex-wrap.gap-0.bg-center > div > div.rounded-sm.px-4.py-1.bg-cover > img"));
            
            // Take screenshot
            File srcFile = captchaImage.getScreenshotAs(OutputType.FILE);
            File captchaFile = new File("captcha.png");
            FileUtils.copyFile(srcFile, captchaFile);
            
            // Process image
            BufferedImage bufferedImage = ImageIO.read(captchaFile);
            BufferedImage preprocessedImage = preprocessImage(bufferedImage);
            
            // Save processed image
            File processedFile = new File("processed_captcha.png");
            ImageIO.write(preprocessedImage, "png", processedFile);
            
            // OCR processing
            ITesseract instance = new Tesseract();
            instance.setDatapath("C:/Program Files/Tesseract-OCR/tessdata");
            instance.setLanguage("eng");
            instance.setTessVariable("tessedit_char_whitelist", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789");
            instance.setTessVariable("tessedit_pageseg_mode", "7");
            
            String captchaText = instance.doOCR(preprocessedImage).trim();
            logger.info("CAPTCHA solved: {}", captchaText);
            return captchaText;
            
        } catch (Exception e) {
            logger.error("Error solving CAPTCHA: {}", e.getMessage());
            return null;
        }
    }
    
    /**
     * Preprocesses image for better OCR accuracy
     * @param originalImage Original BufferedImage
     * @return Preprocessed BufferedImage
     */
    private BufferedImage preprocessImage(BufferedImage originalImage) {
        try {
            // Convert to grayscale
            BufferedImage grayscaleImage = new BufferedImage(
                    originalImage.getWidth(),
                    originalImage.getHeight(),
                    BufferedImage.TYPE_BYTE_GRAY);
            Graphics2D g2d = grayscaleImage.createGraphics();
            g2d.drawImage(originalImage, 0, 0, null);
            g2d.dispose();
            
            // Scale up image
            int scaledWidth = grayscaleImage.getWidth() * 2;
            int scaledHeight = grayscaleImage.getHeight() * 2;
            BufferedImage scaledImage = new BufferedImage(scaledWidth, scaledHeight, BufferedImage.TYPE_BYTE_GRAY);
            Graphics2D g2 = scaledImage.createGraphics();
            
            // Apply rendering hints
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
            g2.drawImage(grayscaleImage, 0, 0, scaledWidth, scaledHeight, null);
            g2.dispose();
            
            return scaledImage;
            
        } catch (Exception e) {
            logger.error("Error preprocessing CAPTCHA image: {}", e.getMessage());
            return originalImage;
        }
    }
}