package com.thriftshopping.Activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import com.thriftshopping.helper.DatabaseHandler;
import com.thriftshopping.webservice.AmazonPurchaseUtility;
import com.thriftshopping.webservice.Model;
import com.thriftshopping.webservice.ServiceType;

import java.util.List;

/**
 * Created by Adi on 4/14/2017.
 */

public class AmazonBag extends AppCompatActivity {
    ListView savedAmazonItems;
    List<Model> items;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.amazon_bag);
        savedAmazonItems = (ListView) findViewById(R.id.savedAmazonItems);
        RetrieveItemsTask retrieveFeedTask3 = new RetrieveItemsTask(getApplicationContext(), savedAmazonItems, ServiceType.AMAZON);
        retrieveFeedTask3.execute();
    }

    public void buyAll(View view) {
        DatabaseHandler mydb = new DatabaseHandler(view.getContext());
        items = mydb.getAllProducts(ServiceType.AMAZON);
        if (items.size() > 0) {
            AmazonPurchaseUtility amazon = new AmazonPurchaseUtility();
            amazon.setEnvironment(items, view);
            amazon.execute();
            view.setEnabled(false);
        }
    }

}
