package com.example.cloudrecovery.DoctorPlanAdapter;

import android.content.Context;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cloudrecovery.R;
import com.example.cloudrecovery.DoctorPlanEntity.Patient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

public class PatientAdapter extends ArrayAdapter<Patient> {
    private int resourceId;
    private Context context;

    /* 重写构造函数，即上文中划横线部分的实现 */
    public PatientAdapter(Context context, int textViewResourceId, List<Patient> objects){
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
        this.context = context;
    }

    /* 重写getView()方法，该方法在每个子项被滚动到屏幕内的时候会被调用 */
    public View getView(int position, View convertView, final ViewGroup parent){
        final Patient patient = getItem(position); //获取当前项的Person实例
        View view;
        ViewHolder viewHolder;  //下文中定义的内部类，用于保存image，name，delete等实例，而不是每次滑动加载时都通过findViewById的方法获得控件实例
        /* getView()方法中的converView参数表示之前加载的布局 */
        if(convertView == null){
            /* 如果converView参数值为null，则使用LayoutInflater去加载布局 */
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder = new ViewHolder();
            /* 调用View的findViewById()方法分别获得image和name实例 */
            viewHolder.photo = (ImageView) view.findViewById(R.id.iv_p_item_photo);
            viewHolder.name = (TextView) view.findViewById(R.id.tv_p_item_name);
            viewHolder.nickName = (TextView) view.findViewById(R.id.tv_p_item_nickname);
            viewHolder.time = (TextView) view.findViewById(R.id.tv_p_item_time);
            viewHolder.nearlyUpdate = (TextView) view.findViewById(R.id.tv_p_item_nearlyupdate);
            viewHolder.totop = view.findViewById(R.id.rl_p_item_totop);
            viewHolder.delete = view.findViewById(R.id.rl_p_item_delete);
            view.setTag(viewHolder);
        }
        else{
            /* 否则，直接对converView进行重用 */
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }

        /* 调用setImageResource()和setText()方法来设置显示的图片和文字 */
        viewHolder.name.setText(patient.getName());
        viewHolder.nickName.setText(patient.getNickname());

        /* 以下黑体为事件监听响应部分，即点击删除图标和头像会分别显示提醒信息 */
        viewHolder.delete = view.findViewById(R.id.rl_p_item_delete);
        viewHolder.delete.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Toast.makeText(getContext(), "删除", Toast.LENGTH_SHORT).show();
            }
        });
        viewHolder.totop.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Toast.makeText(getContext(), "置顶", Toast.LENGTH_SHORT).show();
            }
        });

        return view; //返回布局
    }

    class ViewHolder{
        ImageView photo;
        TextView name;
        TextView nickName;
        TextView nearlyUpdate;
        TextView time;
        View totop;
        View delete;
    }

    //删除
    public void upKeyValueForDelete(final String s, final String name){
        new Thread(){
            @Override
            public void run() {
                try {
                    String keyvalue = "?name="+name;
                    Log.e("SellerUDeletedata发送的",s+keyvalue);
                    URL url  = new URL(s+keyvalue);
                    url.openStream();
                    URLConnection conn = url.openConnection();
                    InputStream in = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(
                            new InputStreamReader(in, "utf-8"));
                    String str = reader.readLine();
                    Log.e("更改"+name+"获取到",str);
                    //关闭流
                    reader.close();
                    in.close();
                    //message
                    Message msg = new Message();
                    if(str.equals("none")){
                        Looper.prepare();
                        Toast.makeText(getContext(), "删除失败",
                                Toast.LENGTH_LONG).show();
                        Looper.loop();
                        msg.what = 2;
                    }else{
                        Looper.prepare();
                        Toast.makeText(getContext(), "删除成功",
                                Toast.LENGTH_LONG).show();
                        Looper.loop();
                        msg.what = 1;
                        msg.obj = str;
                    }
//                    handler.sendMessage(msg);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
