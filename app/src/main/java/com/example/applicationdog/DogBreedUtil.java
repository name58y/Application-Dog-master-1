package com.example.applicationdog;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.TextView;

import com.example.applicationdog.ml.Model;

import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class DogBreedUtil {

    private static final int IMAGE_SIZE = 224;

    public static void processAndDisplay(Context context, Bitmap image, TextView result, TextView detail) {
        try {
            Model model = Model.newInstance(context);
            Bitmap resizedImage = Bitmap.createScaledBitmap(image, IMAGE_SIZE, IMAGE_SIZE, true);
            ByteBuffer byteBuffer = bitmapToBuffer(resizedImage);
            TensorBuffer inputFeature0 = TensorBuffer.createFixedSize(new int[]{1, IMAGE_SIZE, IMAGE_SIZE, 3}, DataType.FLOAT32);
            inputFeature0.loadBuffer(byteBuffer);
            Model.Outputs outputs = model.process(inputFeature0);
            TensorBuffer outputFeature0 = outputs.getOutputFeature0AsTensorBuffer();
            float[] details = outputFeature0.getFloatArray();
            int maxPos = getMaxDetailIndex(details);
            float maxdetail = details[maxPos];

            float detailThreshold = 0.6f;

            String[] classes = {"Golden Retriever", "Siberian Husky", "Beagle", "Thai Bangkaew", "Chihuahua"};
            String[] breedInfos = {
                    "ข้อมูลสายพันธุ์ Golden Retriever โกลเดินรีทรีฟเวอร์ เป็นสายพันธุ์สุนัข มีถิ่นกำเนิดในประเทศสกอตแลนด์ มีขนยาว มีสีขนเหลืองอ่อน เหลืองทอง และเหลืองเข้ม " +
                            "โดยทั่วไปแล้ว เพศผู้ควรมีความสูง ประมาณ 23- 24 นิ้ว และเพศเมียควรมีความสูงประมาณ 21.5-22.5 นิ้ว " +
                            "แต่เดิมนั้นสายพันธุ์นี้ได้ปรับปรุงคุณลักษณะบางอย่างเพื่อในงานของนายพราน",
                    "ข้อมูลสายพันธุ์ Siberian Husky",
                    "ข้อมูลสายพันธุ์ Beagle",
                    "ข้อมูลสายพันธุ์ Thai Bangkaew",
                    "ข้อมูลสายพันธุ์ Chihuahua"
            };

            if (maxdetail < detailThreshold) {
                result.setText("ไม่พบสายพันธุ์นี้ในฐานข้อมูล");
                detail.setText("llllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllll");
            } else {
                result.setText(classes[maxPos]);
                detail.setText(breedInfos[maxPos]);
            }
            model.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static ByteBuffer bitmapToBuffer(Bitmap bitmap) {
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(4 * IMAGE_SIZE * IMAGE_SIZE * 3);
        byteBuffer.order(ByteOrder.nativeOrder());

        int[] intValues = new int[IMAGE_SIZE * IMAGE_SIZE];
        bitmap.getPixels(intValues, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(), bitmap.getHeight());

        int pixel = 0;
        for (int i = 0; i < IMAGE_SIZE; i++) {
            for (int j = 0; j < IMAGE_SIZE; j++) {
                int val = intValues[pixel++];
                byteBuffer.putFloat(((val >> 16) & 0xFF) * (1.f / 255.f));
                byteBuffer.putFloat(((val >> 8) & 0xFF) * (1.f / 255.f));
                byteBuffer.putFloat((val & 0xFF) * (1.f / 255.f));
            }
        }

        return byteBuffer;
    }

    private static int getMaxDetailIndex(float[] details) {
        int maxPos = 0;
        float maxDetail = 0;
        for (int i = 0; i < details.length; i++) {
            if (details[i] > maxDetail) {
                maxDetail = details[i];
                maxPos = i;
            }
        }
        return maxPos;
    }
}
