package uct.cs.networks.utils;
/*      USAGE
    - encodeImage() Takes in filepath of inage and returns a string encoding of it
    - decodeImage() takes in the string encoding generated above and an output file path 
                    where the decoded image will be stored. 

*/

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Base64;

import javax.imageio.ImageIO;

public class ImageProcessor {
    private static byte[] imageToByteArray(BufferedImage image) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", baos);
        return baos.toByteArray();
    }

    private static String encodeImageToBase64(byte[] imageBytes) {
        return Base64.getEncoder().encodeToString(imageBytes);
    }

    private static byte[] decodeBase64Image(String base64Image) {
        return Base64.getDecoder().decode(base64Image);
    }

    private static void saveImageToFile(byte[] imageBytes, String outputPath) throws IOException {
        try (FileOutputStream outputStream = new FileOutputStream(outputPath)) {
            outputStream.write(imageBytes);
        }
    }

    public String encodeImage(String filePath) throws IOException {
        File imageFile = new File(filePath);
        BufferedImage bufferedImage = ImageIO.read(imageFile);
        byte[] imageBytes = imageToByteArray(bufferedImage); // Convert the image to a byte array
        String base64Image = encodeImageToBase64(imageBytes); // Now you can encode the byte array as a Base64 string

        // Write output to file:
        FileWriter writer = new FileWriter("image.txt");
        writer.write(base64Image);
        writer.close();
        return base64Image;
    }

    public void decodeImage(String base64image, String outputPath) {
        String[] parts = base64image.split("CAPTION:");
        // String imagePart = parts[0];
        // String captionPart = parts[1];
        byte[] decodedImage = null;// = decodeBase64Image(imagePart);
        try {
            decodedImage = decodeBase64Image(
                    new String(Files.readAllBytes(Paths.get("image.txt")), StandardCharsets.UTF_8));

        } catch (Exception e) {
        }
        try {
            saveImageToFile(decodedImage, outputPath);
            // System.out.println(captionPart);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
