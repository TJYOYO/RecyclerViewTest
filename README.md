# RecyclerViewTest
RecyclerView 实践学习的demo，其中有基本的，瀑布流，进阶的，等等

##一：表格的实现

最终效果:

![device-2017-03-13-135723.png](http://upload-images.jianshu.io/upload_images/909565-bcb18dd085671ac7.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

代码分析, 其中遇到的问题和坑：

####1:  外围的黑色边框，shape完成  ( bg.xml )
```
<shape xmlns:android="http://schemas.android.com/apk/res/android">

    <!-- 填充-->
    <solid android:color="#ffffff"></solid>
    <!-- 边框 -->
    <stroke android:color="#000000" android:width="2dp"></stroke>
</shape>
```
上面是4面的边框，如果想实现一个3面的边框呢？
**可以使用layer-list的图片叠加功能实现，(bg_header.xml)**
```
<layer-list xmlns:android="http://schemas.android.com/apk/res/android" >

    <!-- 实现了只有left，top，right的3面边框, 底部不需要 -->

    <item>
        <shape android:shape="rectangle" >
            <stroke
                android:width="1dp"
                android:color="@color/black" />
        </shape>
    </item>

    <item
        android:left="2dp"
        android:right="2dp"
        android:top="2dp">
        <!-- 可以完全理解为top就是paddingTop，bottom就是paddingBottom。就是内边距。
        这边的3面有内边距，-->

        <!-- 在实际使用中我发现1dp达不到显示效果，而2dp正好可以显示边框 -->

        <shape android:shape="rectangle" >
            <solid android:color="@color/mainColor" />
        </shape>
    </item>
</layer-list>
```

####2:  列表中的下划线实现

**a:  实现类DividerItemDecoration.class, 见项目代码**

RecyclerView.ItemDecoration，该类为抽象类，官方目前并没有提供默认的实现类。而DividerItemDecoration.class该类很好的实现了RecyclerView添加分割线。

**b: recyclerview 添加item的下划线，和方向**

>mRecyclerView.addItemDecoration(new DividerItemDecoration(
                getActivity(), DividerItemDecoration.VERTICAL_LIST));

**在该demo的表格中下划线的方向是垂直方向，不要弄错，已经犯过了！**

**c:  AppTheme中进行下划线的颜色修改**

```
<style name="AppTheme" parent="Theme.AppCompat.Light.NoActionBar">
        <!-- Customize your theme here. -->
        <item name="colorPrimary">@color/colorPrimary</item>
        <item name="colorPrimaryDark">@color/colorPrimaryDark</item>
        <item name="colorAccent">@color/colorAccent</item>
        //下划线的颜色修改
        <item name="android:listDivider">@drawable/item_bottom_line</item>
    </style>

```
####3:  添加header

思路：adapter提供的getItemViewType()方法，来区分不同的view，加载不同的布局实现。

a: getItemViewType()中根据位置position==0来判断是header布局
```
@Override
    public int getItemViewType(int position) {
        if(position == 0){
            return TYPE_HEADER;
        }

        if (position == getItemCount()-1){
            //最后一个,应该加载Footer
            return TYPE_FOOTER;
        }

        return TYPE_NORMAL;
    }

```
b:  通过判断headerView和类型是不是正确，布局header到recyclerView中
```
@Override
    public NormalViewholder onCreateViewHolder(ViewGroup parent, int viewType) {

        if(mHeaderView != null && viewType == TYPE_HEADER){
            //需要传入parent，不然item不能居中
//            return new NormalViewholder(mHeaderView);
            return new NormalViewholder(LayoutInflater.from(mContext).inflate(R.layout.item_first_header, parent, false));
        }

        //需要传入parent，不然item不能居中
        View viewNormal = LayoutInflater.from(mContext).inflate(R.layout.item_recycler_grid, parent, false);
        return new NormalViewholder(viewNormal);
    }

```
c: 设置header
```
private void setAdaperHeader(){
        View view = LayoutInflater.from(mActivity).inflate(R.layout.item_first_header,null);
        mAdapter.setHeader(view);
    }
```

####坑1：布局中的文字不能居中

解决方法：**布局文件，需要进过layoutinflater加入到parent才行!!!!!**
```
if(mHeaderView != null && viewType == TYPE_HEADER){
            //需要传入parent，不然item不能居中
//            return new NormalViewholder(mHeaderView);
            return new NormalViewholder(LayoutInflater.from(mContext).inflate(R.layout.item_first_header, parent, false));
        }
```
####坑2：给header设置毕竟颜色时，黑色的边框给覆盖了

![device-2017-03-13-135550.png](http://upload-images.jianshu.io/upload_images/909565-ba2873e476e62ad9.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

解决方法：使用layer-list实现一个背景，这个背景是将left，top，right3面的内边距减掉2dp的，这样和原来的布局重叠一下，黑色的边框就出来了！

```
<layer-list xmlns:android="http://schemas.android.com/apk/res/android">

    <item
        android:left="2dp"
        android:right="2dp"
        android:top="2dp">
        <shape>
            <solid android:color="@color/colorAccent"></solid>
        </shape>
    </item>

</layer-list>
```
好了，到这里基本弄完了，有问题的欢迎留言，谢谢！
github地址：https://github.com/George-Soros/RecyclerViewTest

##2：瀑布流的截图, 其中图片的数据，来自抓包网易客户端美女图片的接口获取到的

![image](https://github.com/George-Soros/RecyclerViewTest/blob/master/shot-img.jpg)
