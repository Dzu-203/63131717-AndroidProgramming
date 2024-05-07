package com.ntu.sqllite_book;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       SQLiteDatabase db = openOrCreateDatabase("QLSach.db", // tên file = tên DB
                                                    MODE_PRIVATE,  // giới hạn truy cập
                                                     null          // con trỏ bản ghi
                           );
        //B2. Thực thi câu lệnh select
        String sqlSelect ="Select * from Book;";
        Cursor cs = db.rawQuery(sqlSelect,null);
        cs.moveToFirst(); // đưa con trỏ bản ghi về hàng đầu tiên
        ArrayList<Book> dsSach = new ArrayList<Book>();
            //3.3. Duyệt qua lần lượt từng bản ghi và thêm vào danhSach
        while (cs.moveToNext()) // còn bản ghi để chuyển tới
        {
            // Lấy dữ liệu từng côột ở dòng hiện tại
            int idSach = cs.getInt(0);  // lấy dữ liệu ở côt 0, kiểu int
            String tenSach = cs.getString(1);
            int soTrang = cs.getInt(2);
            float gia = cs.getFloat(3);
            String mota = cs.getString(4);
            // Tạo một đối tượng sách và thêm vào danh sách
            Book b = new Book(idSach,tenSach,soTrang,gia,mota);
            dsSach.add(b);
        }
       ArrayList<String> dsTenSach = new ArrayList<String>();
       for (int i=0; i<dsSach.size(); i++ )
           dsTenSach.add(dsSach.get(i).getBookName());


    }

    ArrayList<Book> getDataForRY() {
        //B1. Mở CSDL
        SQLiteDatabase db = openOrCreateDatabase("QLSach.db",
                MODE_PRIVATE,
                null
        );
        String sqlSelect ="Select * from Book;";
        Cursor cs = db.rawQuery(sqlSelect,null);
        cs.moveToFirst();
        ArrayList<Book> dsSach = new ArrayList<Book>();
        while (cs.moveToNext())
        {
            int idSach = cs.getInt(0);
            String tenSach = cs.getString(1);
            int soTrang = cs.getInt(2);
            float gia = cs.getFloat(3);
            String mota = cs.getString(4);
            Book b = new Book(idSach,tenSach,soTrang,gia,mota);
            dsSach.add(b);
        }
       return  dsSach;
    }

    ArrayList<String> getTenSachForRY(ArrayList<Book> dsSach) {
        ArrayList<String> dsTenSach = new ArrayList<String>();
        for (int i=0; i<dsSach.size(); i++ )
            dsTenSach.add(dsSach.get(i).getBookName());
        return dsTenSach;
    }
}