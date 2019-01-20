package platform.svc.thr.com.thrsvcplatform;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import platform.svc.thr.com.thrsvcplatform.Adaptor.ItemListAdaptor;
import platform.svc.thr.com.thrsvcplatform.vo.SellerItem;

public class ItemListSellerActivity extends AppCompatActivity {

    private List<SellerItem> allItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list_seller);

        // RecyclerView 생성
        allItems = new ArrayList<>();
        final ItemListAdaptor itemListAdaptor = new ItemListAdaptor(allItems);

        // ReCyclerView의 객체마다 OnClick 이벤트 적용을 위한 생성자.
        itemListAdaptor.setOnItemDetailClickedListener(new ItemListAdaptor.OnItemDetailClickedListener() {
            // OnClick 이벤트발생시 ItemDetailSellerActivity를 호출하기 위한 정의
            @Override
            public void onItemDetailClickedListener(View view, SellerItem item, int position) {

                // this 접근이 되지 않으며 getApplicationContext로 접근을 해야한다. 이유는 Inner Class 안에 접근을 해야하므로.
                final Intent intentItemDetailSeller = new Intent(getApplicationContext(), ItemDetailSellerActivity.class);

                // 통신용 데이터를 만든다.
                // ItemListSellerActivity.itemListAdaptor.onItemDetailClickedListener -> ItemDetailSellerActivity 로 데이터 연동
                intentItemDetailSeller.putExtra("itemNum","S1234564789");
                intentItemDetailSeller.putExtra("itemName","상품이름입니다");
                intentItemDetailSeller.putExtra("itemPrice",99520);

                // Activity가 중첩해서 메모리에 올라오는 것을 방지한다. 잘모르겠으면 옵션을 그냥 이걸로 줘라. 반드시
                intentItemDetailSeller.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

                // Android.Api.startActivity 를 호출하여 Activity를 활성화 한다. 프로그램을 호출한다고 보면된다.
                startActivity(intentItemDetailSeller);

                // 화면이 열리거나 닫을때에 적용되는 이벤트이다. <- 옵션은 fade_in -> 옵션은 fade_out
                // Acitivity를 finsh() 하면 Android 자체에서 stack에 쌓인 intent를 죽이는 작업을 시작한다.
                // 단 종료 시점은 알수 없다. 없어진 상황을 callback을 받을 수 없다.
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });


        final RecyclerView recyclerView    = findViewById(R.id.recycler_view_all_items);

        // 1. 메인 화면인 판매자 메인 화면에서 하단의 그리드를 구성하는 부분이며 컨테츠 갯수는 spanCount로 조절한다. 현재 3개
        final RecyclerView.LayoutManager layoutManager   = new GridLayoutManager(this, 2);

        // 1번으로 선언된 layoutManager를 recyclerView에 연결.
        recyclerView.setLayoutManager(layoutManager);
        // ItemListAdaptor를 recyclerView에 연결한다.
        recyclerView.setAdapter(itemListAdaptor);

        // recyclerView의 데이터자체가 비어있으므로 샘플링데이터로 화면을 구성하기 위해 임시적으로 데이터를 생성하여 연결한다.
        prepareAllItems();

        // 1. Component init Spinner
        initSpinner();
    }

    /**
     * onCreate.initSpinner
     */
    private void initSpinner() {

        final Spinner spinner = findViewById(R.id.spinner_menu_item_list_seller);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            /**
             * Selected.Callback
             * @param parent
             * @param view
             * @param position
             * @param id
             */
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                final StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("parent : ")
                             .append(parent.getItemAtPosition(position));
                Toast.makeText(getApplicationContext(),stringBuilder.toString(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // Spinner Drop down elements
        List<String> categories = new ArrayList<>();
        categories.add(getResources().getString(R.string.spinner_order_name));
        categories.add(getResources().getString(R.string.spinner_order_date));
        categories.add(getResources().getString(R.string.spinner_order_sale));
        categories.add(getResources().getString(R.string.spinner_order_favorite));


        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

    }


    /**
     * 전체 아이템 목록을 준비하는 메소드
     */
    private void prepareAllItems() {

        for( int idx = 0; idx < 30; ++idx ) {
            final StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("아이템").append(" : ").append(idx);
            SellerItem sellerItem = new SellerItem(stringBuilder.toString());
            allItems.add(sellerItem);
        }
    }
}
