package cn.edu.gdmec.chaos07150844.viewflipperdemo;


import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener {
    private int[] images = {R.drawable.icon1,
            R.drawable.icon2,
            R.drawable.icon3,R.drawable.icon4,
            R.drawable.icon5};
    private GestureDetector gestureDetector = null;
    private ViewFlipper viewFlipper=null;
    private static final int a=100;
    private static final int b=200;
    private Activity activity =null;
    private Animation ri,ro,li,lo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activity=this;
        viewFlipper= (ViewFlipper) findViewById(R.id.viewFlipper);
        gestureDetector = new GestureDetector(this, this);
        ri = AnimationUtils.loadAnimation(activity,R.anim.push_right_in);
        ro=AnimationUtils.loadAnimation(activity,R.anim.push_right_out);
        li=AnimationUtils.loadAnimation(activity,R.anim.push_left_in);
        lo=AnimationUtils.loadAnimation(activity,R.anim.push_left_out);

        for(int i =0;i<images.length;i++){
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(images[i]);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView,i,new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));
        }
        viewFlipper.setAutoStart(true);
        viewFlipper.setFlipInterval(2000);
        viewFlipper.startFlipping();

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        viewFlipper.stopFlipping();
        viewFlipper.setAutoStart(false);
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        if(e2.getX()-e1.getX()>a&&Math.abs(velocityX)>b){
            viewFlipper.setInAnimation(li);
            viewFlipper.setOutAnimation(lo);
            setTitle("图片编号:"+viewFlipper.getDisplayedChild());
        }else if(e1.getX()-e2.getX()>a&&Math.abs(velocityX)>b){
            viewFlipper.setInAnimation(ri);
            viewFlipper.setOutAnimation(ro);
            setTitle("图片编号:"+viewFlipper.getDisplayedChild());
            return true;


        }
        return true;
    }
}
