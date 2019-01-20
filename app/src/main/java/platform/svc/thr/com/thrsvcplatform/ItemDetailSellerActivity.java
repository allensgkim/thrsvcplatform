package platform.svc.thr.com.thrsvcplatform;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import platform.svc.thr.com.thrsvcplatform.Adaptor.ItemDetailProdAdaptor;

public class ItemDetailSellerActivity extends AppCompatActivity {

    private List<String> detailContents;
    String itemName;
    int    itemNum;
    int    itemPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail_seller);

        // Intent로 ItemDetailSellerActivity가 생성되었으므로 화면간 통신(파라메터 전송된 데이터를 받아준다.)
        final Intent intent = getIntent();
        itemNum   = intent.getIntExtra("itemNum", -1);
        itemName  = intent.getStringExtra("itemName");
        itemPrice = intent.getIntExtra("itemPrice", -1);

        // 상품 사진
        final ImageView imageView = findViewById(R.id.image_view_item_detail_seller);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "사진 확대 화면 띄우기", Toast.LENGTH_SHORT).show();
            }
        });
        // ItemDetailProdAdaptor 를 연결하는 작업을 시작한다.
        detailContents = new ArrayList<>();
        // 순서를 잘 지켜줘야한다.. 아니면 NullPointException 또는 RuntimeException이 발생하므로 반드시 순서를 준수한다.
        // 1. ItemDetailProdAdaptor를 생성한다.
        final ItemDetailProdAdaptor      itemDetailProdAdaptor = new ItemDetailProdAdaptor(detailContents);
        // 2. RecyclerView를 findViewById로 선언한다.
        final RecyclerView               recyclerView          = findViewById(R.id.recycler_view_detail_comments);
        // 3. LayoutManager를 생성한다.
        final RecyclerView.LayoutManager layoutManager         = new LinearLayoutManager(this);
        // 4. layoutManger를 recyclerView에 연결한다.
        recyclerView.setLayoutManager(layoutManager);
        // 5. itemDetailProdAdaptor를 recyclerView에 연결한다.
        recyclerView.setAdapter(itemDetailProdAdaptor);

        // 기초데이터는 Db조회로 이루어져야 하지만 지금은 테스트용도로 만들어서 적용한다.
        prepareComments();
    }
    /**
     * 코멘드 목록을 준비하는 메소드
     */
    private void prepareComments() {
        detailContents.add("상품 이름 : "+itemName);
        detailContents.add("상품 번호 : "+itemNum);
        detailContents.add("상품 가격 : "+itemPrice+" 원");

        for( int idx = 0; idx < 10; ++idx ) {
            final StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("코멘트").append(" : ").append(idx);
            detailContents.add(stringBuilder.toString());
        }
    }

    /**
     * 테스트용도로 공유하기 버튼을 제어하기 위한 onclicked Event 이다.
     * @param view
     */
    public void btnItemDetailSellerClicked(View view){
        Toast.makeText(this, "공유하기", Toast.LENGTH_SHORT).show();
    }
}
