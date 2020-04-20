package tj.stamenbank.car_db;

import androidx.appcompat.app.AppCompatActivity;
import tj.stamenbank.car_db.Data.DatabaseHandler;
import tj.stamenbank.car_db.Model.Car;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView txt = (TextView) findViewById(R.id.text);
        DatabaseHandler databaseHandler = new DatabaseHandler(this);
//        databaseHandler.addCar(new Car("BMW", "30000"));
//        databaseHandler.addCar(new Car("Range", "2500"));
//        databaseHandler.addCar(new Car("Mersedes", "1800"));
//        databaseHandler.addCar(new Car("Kia", "10000"));
//        databaseHandler.addCar(new Car("Porsche", "40000"));
//        databaseHandler.addCar(new Car("Lambo", "50000"));
//
//        List<Car> carList =databaseHandler.getAllCars();
//        for (Car car : carList){
//            Log.d("Car info","ID: "+car.getId()+", name "+car.getName()+", price "+car.getPrice());
//
//        }
        //Car car = databaseHandler.getCar(1);
        List<Car> carList = databaseHandler.getAllCars();
        for (Car car : carList) {
            Log.d("Car info", "ID: " + car.getId() + ", name " + car.getName() + ", price " + car.getPrice());
            txt.append("ID: " + car.getId() + ", name " + car.getName() + ", price " + car.getPrice()+"\n");
        }
    }
}
