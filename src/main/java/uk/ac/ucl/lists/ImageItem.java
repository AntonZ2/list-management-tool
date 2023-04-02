package uk.ac.ucl.lists;

import java.util.Base64;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

class ImageItem extends Item {

    public ImageItem(){
    }

    public ImageItem(String key, String imageData) {
        this.key = key;
        this.data = encodeImage(imageData);
    }

    @Override
    public boolean search(String keyword) {
        return key.toLowerCase().contains(keyword.toLowerCase());
    }

    private String encodeImage(String imagePath) {
        try {
            byte[] imageBytes = Files.readAllBytes(Paths.get(imagePath));
            String encodedImage = Base64.getEncoder().encodeToString(imageBytes);
            return encodedImage;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean isImage() {
        return true;
    }
}
