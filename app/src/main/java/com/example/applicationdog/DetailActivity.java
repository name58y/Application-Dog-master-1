package com.example.applicationdog;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView imageViewDog = findViewById(R.id.imageViewDog);
        TextView textViewBreed = findViewById(R.id.textViewBreed);
        TextView textViewDescription = findViewById(R.id.textViewDescription);

        String dogName = getIntent().getStringExtra("dogName");

        // Set breed name
        textViewBreed.setText(dogName);

        // Set image and description based on dog name
        if (dogName.equals("Golden Retriever")) {
            imageViewDog.setImageResource(R.drawable.gole);
            textViewDescription.setText("อายุเฉลี่ย:\t10–12 ปี\n" +
                    "น้ำหนัก:\t27–34 กิโลกรัม\n" +
                    "ส่วนสูง:\t51–61 เซนติเมตร\n" +
                    "สีขน:\tThe Golden Retriever comes in various shades of gold from light to dark\n" +
                    "ขนาดตัว:\tขนาดใหญ่\n" +
                    "ชื่อกลุ่มคนเลี้ยงสุนัข:\tสุนัขคาบเหยื่อ" +
                    "ลักษณะนิสัย\n" +
                    "สุนัขโกลเด้น รีทริฟเวอร์ นั้นเป็นสุนัขที่เป็นมิตรและนิสัยดีเป็นอันดับต้นๆ ในบรรดาสุนัขทั้งหมด แต่เดิมสุนัขโกลเด้น รีทริฟเวอร์ ถูกปรับปรุงพันธุ์มาเพื่อเป็นผู้ช่วยของบรรดาผู้ที่ชื่นชอบการล่านกเป็ดน้ำ สุนัขพันธุ์นี้จึงค่อนข้างแอคทีฟ และมีพลังงานสูง มีนิสัยชอบคาบ รวมถึงกัดแทะสิ่งของต่าง ๆ");
        } else if (dogName.equals("Siberian Husky")) {
            imageViewDog.setImageResource(R.drawable.si);
            textViewDescription.setText("อายุเฉลี่ย:\t12–15 ปี\n" +
                    "น้ำหนัก:\t16–27 กิโลกรัม\n" +
                    "ส่วนสูง:\t51–60 เซนติเมตร\n" +
                    "สีขน:\tBlack and white, grey, white, black, black and tan, silver, sable and white, grey and white, red and white\n" +
                    "ขนาดตัว:\tขนาดกลาง\n" +
                    "ชื่อกลุ่มคนเลี้ยงสุนัข:\tสำหรับทำงาน");
        } else if (dogName.equals("Beagle")) {
            imageViewDog.setImageResource(R.drawable.bea);
            textViewDescription.setText("อายุเฉลี่ย:\t12-15 ปี\n" +
                    "น้ำหนัก:\t10-11 กิโลกรัม\n" +
                    "ส่วนสูง:\t33-40 เซนติเมตร\n" +
                    "สีขน:\tTricolour (black, tan and white); blue, white and tan; badger pied; hare pied; lemon pied; lemon and white; red and white; tan and white; black and white; all white. These colours can also be mottled, with the exception of all white\n" +
                    "ขนาดตัว:\tขนาดกลาง\n" +
                    "ชื่อกลุ่มคนเลี้ยงสุนัข:\tพันธุ์นักล่า");
        } else if (dogName.equals("Thai Bangkaew")) {
            imageViewDog.setImageResource(R.drawable.ba);
            textViewDescription.setText("อายุขัย\n" +
                    "สุนัขพันธุ์ไทยบางแก้ว โดยทั่วไปมีอายุขัยอยู่ที่ประมาณ 10-12 ปี\n" +
                    "\n" +
                    "ลักษณะนิสัย\n" +
                    "สุนัขพันธุ์ไทยบางแก้ว เป็นสุนัขที่จำเป็นต้องได้รับการฝึกฝน สามารถฝึกเพื่อใช้งาน หรือสามารถฝึกให้เป็นสุนัขเฝ้าบ้านได้ มีนิสัยที่ค่อนข้างตื่นตัว หวงสิ่งของและหวงเจ้าของ ร่าเริง กล้าหาญ เชื่อมั่นในตัวเอง จิตประสาทมั่นคงไม่ขี้กลัว หรือตื่นตกใจง่าย ซื่อสัตย์ ฉลาด");
        } else if (dogName.equals("Chihuahua")) {
            imageViewDog.setImageResource(R.drawable.ch);
            textViewDescription.setText("ลักษณะนิสัย\n" +
                    "สุนัขสายพันธุ์ชิวาวา อยู่ในกลุ่มสุนัขเลี้ยงเป็นเพื่อน จัดว่าเป็นสายพันธุ์ที่มีความสง่างาม มีความกระตือรือร้น โดดเด่นที่ลักษณะของหัวที่กลมคล้ายกับลูกแอปเปิ้ล ตากลมโตแต่ไม่โปนออกมา ใบหูมีขนาดใหญ่ตั้งตรง กิจกรรมที่ชอบหนีไม่พ้นการอยู่ใกล้ๆ กับเจ้านายอันเป็นที่รัก และตามติดไปทุกที่ " +
                    "พร้อมที่จะกระโดดลงกระเป๋าทันทีที่เจ้าของจะพาออกไปช้อปปิ้ง");
        }
    }
}
