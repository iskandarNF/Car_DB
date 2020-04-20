package tj.stamenbank.car_db.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import tj.stamenbank.car_db.Model.Car;
import tj.stamenbank.car_db.Utils.Util;

public class DatabaseHandler extends SQLiteOpenHelper {

    public DatabaseHandler( Context context) {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CARS_TABLE="create table " +Util.TABLE_NAME+"("+Util.KEY_ID+" INTEGER PRIMARY KEY,"
                +Util.KEY_NAME+" text,"+Util.KEY_PRICE+" text"+")";
        db.execSQL(CREATE_CARS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP table if exists "+Util.TABLE_NAME);
        onCreate(db);
    }
    public void addCar(Car car){
        SQLiteDatabase db =this.getWritableDatabase();
        //contentvalue необходим для взаимодействия с БД. Пара ключ-значение
        ContentValues contentValues=new ContentValues();
        //Util.KEY_NAME-ключ, car.getName()-значение
        contentValues.put(Util.KEY_NAME, car.getName());
        contentValues.put(Util.KEY_PRICE, car.getPrice());

        db.insert(Util.TABLE_NAME, null, contentValues);
        db.close();
    }
    public Car getCar(int id){
        SQLiteDatabase db =this.getReadableDatabase();
        //cursor позволяет перемещаться по базе данных
        Cursor cursor=db.query(Util.TABLE_NAME, new String[]{Util.KEY_ID,Util.KEY_NAME,Util.KEY_PRICE},
                Util.KEY_ID+"=?",
                new String[]{String.valueOf(id)}, null,null,null,null);

        if (cursor!=null){
            cursor.moveToFirst();
        }
        //0 index-id; 1 index-name; 2 index-price;
        //Извлекаем всю инфу из записи и помемещаем во вновь созданный объект Car; в конце возвращаем.
        Car car=new Car(Integer.parseInt(cursor.getString(0)),cursor.getString(1),
                cursor.getString(2));
        return car;

    }
    public List<Car> getAllCars(){
        SQLiteDatabase db=this.getReadableDatabase();
        List<Car> carsList=new ArrayList<>();
        String selectAllCars="SELECT * FROM "+Util.TABLE_NAME;
        Cursor cursor=db.rawQuery(selectAllCars, null);
        if (cursor.moveToFirst()){
            do {
                Car car=new Car();
                car.setId(Integer.parseInt(cursor.getString(0)));
                car.setName(cursor.getString(1));
                car.setPrice(cursor.getString(2));
                carsList.add(car);
            }while (cursor.moveToNext());
        }
        return carsList;

    }
}
 