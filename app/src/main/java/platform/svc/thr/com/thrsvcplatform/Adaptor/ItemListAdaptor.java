package platform.svc.thr.com.thrsvcplatform.Adaptor;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import platform.svc.thr.com.thrsvcplatform.R;
import platform.svc.thr.com.thrsvcplatform.vo.SellerItem;

public class ItemListAdaptor extends RecyclerView.Adapter<ItemListAdaptor.MyViewholder> {

    // SellerItem.vo 데이터 Object
    private List<SellerItem> itemList;
    // Google님 제공 및 신기하다!
    private OnItemDetailClickedListener onItemDetailClickedListener;

    /**
     * 셍성자
     * @param itemList
     */
    public ItemListAdaptor(List<SellerItem> itemList) {
        this.itemList = itemList;
    }

    /**
     * RecyclerView 의 Click Event연결을 위한 onItemDetailClickedListener의 생성자가 된다.
     * @param onItemDetailClickedListener
     */
    public void setOnItemDetailClickedListener( OnItemDetailClickedListener onItemDetailClickedListener ) {
        this.onItemDetailClickedListener = onItemDetailClickedListener;
    }

    /**
     * 다시 그릴 대상Component를 가지고있을 Class
     * recyclerView는 Component 재활용을 해서 그리기 때문에 빠르다.
     * 그러므로 자원도 덜 소모 되기때문에 추천된다.
     */
    public class MyViewholder extends RecyclerView.ViewHolder {

        public View view;
        public TextView itemName;

        public MyViewholder(@NonNull View itemView) {
            super(itemView);
            this.view = itemView;
            this.itemName = itemView.findViewById(R.id.text_view_item_name);
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////              implements method start            /////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * 최초 화면을 그릴때 생성자에 전달되는 View Object
     * ItemListAdaptor 생성자 이후 상속메드중 자동으로 onCreateViewHolder가 실행이 된다.
     * 1. onCreateViewHolder ->
     * 2. new MyViewhloder(itemView) ->
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public MyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext())
                                            .inflate(R.layout.seller_item_entity, parent, false)
                                            ;
        return new MyViewholder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewholder holder, final int position) {

        // 현재 위치의 position.SellerItem
        final SellerItem sellerItem = itemList.get(position);
        // 아이탬이름
        holder.itemName.setText(sellerItem.getItemName());

        // onClicked Event 연결. Inner Class에서 사용되는 객체 타입은 final이여야 사용이 가능하다.
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Toast를 위한 테스트 작업
                final Context context = view.getContext();
                Toast.makeText(context, "아이템 "+ position +" 클릭", Toast.LENGTH_LONG).show();

                // 최초 생성자에서 listner를 생성하지 않는 경우 반드시 처리해야한다. 에러피하기 위함
                if( onItemDetailClickedListener != null ){
                    onItemDetailClickedListener.onItemDetailClickedListener(view, new SellerItem("자세한 아이템"), position);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////               implements method end             /////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////





    ////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////       Activity간에 데이터 통신을 위한 interface   ////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    public interface OnItemDetailClickedListener {
        void onItemDetailClickedListener(View view, SellerItem item, int position);
    }


}
