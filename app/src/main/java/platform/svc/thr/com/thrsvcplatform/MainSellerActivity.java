package platform.svc.thr.com.thrsvcplatform;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import platform.svc.thr.com.thrsvcplatform.Adaptor.ItemListAdaptor;
import platform.svc.thr.com.thrsvcplatform.vo.SellerItem;

public class MainSellerActivity extends AppCompatActivity {

    private List<SellerItem> sellerItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_seller);

        // RecyclerView 생성
        sellerItems = new ArrayList<>();
        final ItemListAdaptor            itemListAdaptor = new ItemListAdaptor(sellerItems);
        final RecyclerView               recyclerView    = findViewById(R.id.recycler_view_main_seller);

        // 1. 메인 화면인 판매자 메인 화면에서 하단의 그리드를 구성하는 부분이며 컨테츠 갯수는 spanCount로 조절한다. 현재 3개
        final RecyclerView.LayoutManager layoutManager   = new GridLayoutManager(this, 2);

        // 1번으로 선언된 layoutManager를 recyclerView에 연결.
        recyclerView.setLayoutManager(layoutManager);
        // ItemListAdaptor를 recyclerView에 연결한다.
        recyclerView.setAdapter(itemListAdaptor);

        // recyclerView의 데이터자체가 비어있으므로 샘플링데이터로 화면을 구성하기 위해 임시적으로 데이터를 생성하여 연결한다.
        prepareSellerItems();
    }

    /**
     * 판매자 아이템 목록을 준비하는 메소드
     */
    private void prepareSellerItems() {

        for( int idx = 0; idx < 30; ++idx ) {
            final StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("아이템").append(" : ").append(idx);
            SellerItem sellerItem = new SellerItem(stringBuilder.toString());
            sellerItems.add(sellerItem);
        }
    }


    public void btnMainSeller01Clicked(View view){
        Toast.makeText(this, "내 코멘트 클릭!", Toast.LENGTH_SHORT).show();
    }

    public void btnMainSeller02Clicked(View view){
        Toast.makeText(this, "내 주문 목록 클릭!", Toast.LENGTH_SHORT).show();

    }

    /**
     * 새로운 activity를 띄운다.
     * @param view
     */
    public void btnMainSeller03Clicked(View view){
        Toast.makeText(this, "남들이 올린 상품까지 보는 전체 상품 목록 클릭!", Toast.LENGTH_SHORT).show();
        final Intent intentItemListSeller = new Intent(getApplicationContext(), ItemListSellerActivity.class);
        // 이 이후로 생성되는 액티비티가 중복되는 경우 중복을 제거한다.
        intentItemListSeller.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intentItemListSeller);
    }
}
