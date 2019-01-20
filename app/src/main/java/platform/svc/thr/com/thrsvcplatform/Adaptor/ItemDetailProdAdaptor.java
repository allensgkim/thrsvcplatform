package platform.svc.thr.com.thrsvcplatform.Adaptor;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import platform.svc.thr.com.thrsvcplatform.R;

public class ItemDetailProdAdaptor extends RecyclerView.Adapter<ItemDetailProdAdaptor.MyViewHolder> {

    private List<String> commentList;
    // 상단의 아이탬관련 컨텐츠를 채우는 부분을 구분하기위한 구분자
    private final int VIEW_TYPE_INFO    = 20223;
    // 하단의 코멘트관련 컨텐츠를 채우는 부분을 구분하기위한 구분자
    private final int VIEW_TYPE_COMMENT = 30223;

    /**
     * Constructor 선언.
     * @param commentList
     */
    public ItemDetailProdAdaptor(List<String> commentList) {
        this.commentList = commentList;
    }

    /**
     * 두개의 View Holder를 정의하기위한 MyViewHolder Class를 선언한다.
     */
    public abstract class MyViewHolder extends RecyclerView.ViewHolder {

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        // ViewHolder가 서로 다른 데이터를 가지고 서로 다른 view를 생성하므로 서로 다른 View를 그리도록 추상메서드를 만들어둔다.
        public abstract void setDataOnView(int position);
    }

    /**
     * 상단의 상품 상세 정보를 보여줄 Class이며 특정 상황이 아니면 타 Class로 정의 해서 가져오지 말것 메모리 등 최선의 방법인듯
     */
    public class ItemDetailInfo extends MyViewHolder {

        public View view;
        public TextView  itemDetailInfoTextView;
        public ImageView itemDetailImageView;

        /**
         * 생성자
         * @param itemView
         */
        public ItemDetailInfo(@NonNull View itemView) {
            super(itemView);
            this.view = itemView;
            this.itemDetailInfoTextView = itemView.findViewById(R.id.text_view_item_info_name);
            this.itemDetailImageView    = itemView.findViewById(R.id.image_view_item_info_picture);
        }

        /**
         * 연결 대상의 값을 set한다. 이미지는 향후 Url로 변경을 해야한다.
         * 상세 설명란의 데이터를 호출한 값으로 매핑한다.
         * @param position
         */
        @Override
        public void setDataOnView(int position) {

            final String itemName = commentList.get(position);
            this.itemDetailInfoTextView.setText(itemName);
            this.itemDetailImageView.setImageResource(R.drawable.item_detail);
        }
    }

    /**
     * 하단의 코멘트list를 정의한다.
     */
    public class ItemDetailComment extends MyViewHolder {

        public View     itemDetailCommnetView;
        public TextView itemDetailCommnetTextView;

        public ItemDetailComment(@NonNull View itemView) {
            super(itemView);
            this.itemDetailCommnetView     = itemView;
            this.itemDetailCommnetTextView = itemView.findViewById(R.id.text_view_comment);
        }

        /**
         * 연결 대상의 값을 set한다. 이미지는 향후 Url로 변경을 해야한다.
         * 상세 설명란의 데이터를 호출한 값으로 매핑한다.
         * @param position
         */
        @Override
        public void setDataOnView( final int position) {
            // 초기값을 set
            final String itemDetailCommentCommentContent = commentList.get(position);
            this.itemDetailCommnetTextView.setText(itemDetailCommentCommentContent);

            // Event onClick 정의. 테스트 용도로 해당 이벤트 적용시 OnClick 되는지 확인하기 위해서.
            // 글자클릭이 아니라 View 전체에 적용을 해서 테스트한다.
            this.itemDetailCommnetView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Toast.makeText(context, "코멘트 "+position +" 클릭", Toast.LENGTH_LONG).show();
                }
            });
        }
    }


    /**
     * View를 그리는 작업을 하는 onCretaeViewHolder 서로 다른 Layout을 그리기위해 타입을 구분해서 그려준다. 선언은 위에
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public ItemDetailProdAdaptor.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType){
            case VIEW_TYPE_INFO:
                return new ItemDetailInfo(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.seller_item_entity_info, parent, false));
            case VIEW_TYPE_COMMENT:
                return new ItemDetailComment(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.seller_item_entity_comment, parent, false));
        }
        return null;
    }

    /**
     * Holder를 연결한다.
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull ItemDetailProdAdaptor.MyViewHolder holder, int position) {
        holder.setDataOnView(position);
    }

    @Override
    public int getItemCount() {
        return commentList.size();
    }

    /**
     * Generate 하여 Override Method를 만든다.
     * 상품 상세 목록을 확인하면 색상으로 구분하여 보여주고 있다 확인해 볼것.
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        return position < 3 ? VIEW_TYPE_COMMENT : VIEW_TYPE_INFO;
    }
}
