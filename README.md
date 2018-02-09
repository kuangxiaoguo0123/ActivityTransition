# ActivityTransition
Activity transition animation
# Introduction
Activity Transition Animation是Android5.0系统提供的一种新的Activity切换动画，主要有：
>-  slide(位移)
>-  fade(渐变)
>- explode(扩散)
>-  Activity shared elements(Activity view间动画切换)

# Slide
![](https://github.com/kuangxiaoguo0123/ActivityTransition/blob/master/screenshots/slideAnimation.gif)

> ContactActivity.java
在onCreate()方法中添加动画
```java
  private void setSlideAnimation() {
      Slide exitTransition = new Slide(Gravity.TOP);
      exitTransition.setDuration(500);
      //设置Activity退出动画
      getWindow().setExitTransition(exitTransition);
      Slide reenterTransition = new Slide(Gravity.TOP);
      reenterTransition.setDuration(500);
      //设置状态栏不执行动画 false为默认执行
      reenterTransition.excludeTarget(android.R.id.statusBarBackground, true);
      //设置Activity再次进入时动画
      getWindow().setReenterTransition(reenterTransition);
  }
```
> ContactDetailActivity.java
```java
  private void setSlideAnimation() {
      Slide enterTransition = new Slide(Gravity.BOTTOM);
      enterTransition.setDuration(500);
      enterTransition.excludeTarget(android.R.id.statusBarBackground, true);
      //设置Activity进入动画
      getWindow().setEnterTransition(enterTransition);
      Slide returnTransition = new Slide(Gravity.BOTTOM);
      returnTransition.setDuration(500);
      returnTransition.excludeTarget(android.R.id.statusBarBackground, true);
      getWindow().setReturnTransition(returnTransition);
  }
```
> Activity跳转
```
  Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) mContext).toBundle();
  Intent intent = new Intent(mContext, ContactDetailActivity.class);
  ActivityCompat.startActivity(mContext, intent, bundle);
```
# Fade
![](https://github.com/kuangxiaoguo0123/ActivityTransition/blob/master/screenshots/fadeAnimation.gif)
> ContactDetailActivity.java
```java
  private void setFadeAnimation() {
      Fade fade = new Fade();
      fade.setDuration(500);
      getWindow().setEnterTransition(fade);
      getWindow().setReturnTransition(fade);
  }
```
# Explode
![](https://github.com/kuangxiaoguo0123/ActivityTransition/blob/master/screenshots/explodeAnimation.gif)
>ContactActivity.java
```java
  private void setExplodeAnimation() {
      Explode explode = new Explode();
      explode.setDuration(500);
      getWindow().setExitTransition(explode);
      getWindow().setReenterTransition(explode);
  }
```
>ContactDetailActivity.java
```java
  private void setExplodeAnimation() {
      Explode explode = new Explode();
      explode.setDuration(500);
      getWindow().setEnterTransition(explode);
      getWindow().setReturnTransition(explode);
  }
```
# Shared elements between activities
![](https://github.com/kuangxiaoguo0123/ActivityTransition/blob/master/screenshots/shareElements.gif)
>item_contact_list.xml
指定android:transitionName属性
```
  <TextView
      android:id="@+id/contact_name_textView"
      android:layout_width="match_parent"
      android:layout_height="54dp"
      android:background="@drawable/contact_item_press_ripple"
      android:gravity="center_vertical"
      android:paddingLeft="14dp"
      android:textColor="#333333"
      android:transitionName="@string/contact_transition_name"
      android:textSize="16sp" />
```
>activity_contact_detail.xml

```
  <TextView
      android:id="@+id/content_text_view"
      android:layout_width="wrap_content"
      android:layout_height="60dp"
      android:layout_marginLeft="14dp"
      android:gravity="center_vertical"
      android:textColor="#333333"
      android:textSize="40sp"
      android:transitionName="@string/contact_transition_name" />
```
>ContactAdapter.java
```
  String transitionName = mContext.getResources().getString(R.string.contact_transition_name);
  Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) mContext,
          holder.contactNameTextView, transitionName).toBundle();
  Intent intent = new Intent(mContext, ContactDetailActivity.class);
  intent.putExtra(ContactDetailActivity.TEXT_TAG, model.getName());
  ActivityCompat.startActivity(mContext, intent, bundle);
```
>ContactDetailActivity.java
监听Activity动画结束后展示ImageView Reveal效果
```java
  private void setFadeAnimation() {
      Fade fade = new Fade();
      fade.setDuration(500);
      getWindow().setEnterTransition(fade);
      getWindow().setReturnTransition(fade);
      fade.addListener(new Transition.TransitionListener() {
          @Override
          public void onTransitionStart(Transition transition) {
          }
          @Override
          public void onTransitionEnd(Transition transition) {
              createRevealAnimation();
          }
          @Override
          public void onTransitionCancel(Transition transition) {
          }
          @Override
          public void onTransitionPause(Transition transition) {
          }
          @Override
          public void onTransitionResume(Transition transition) {
          }
      });                                                     
  }      

  private void createRevealAnimation() {
      mImageView.setVisibility(View.VISIBLE);
      int width = mImageView.getWidth();
      int height = mImageView.getHeight();
      int radius = Math.max(width, height);
      int centerX = width / 2;
      int centerY = height / 2;
      Animator animator = ViewAnimationUtils.createCircularReveal(mImageView, centerX, centerY, 0, radius);
      animator.setDuration(1000);
      animator.start();
  }                                                         
```
# Sample source code
[https://github.com/kuangxiaoguo0123/ActivityTransition](https://github.com/kuangxiaoguo0123/ActivityTransition)

# More information
[http://blog.csdn.net/kuangxiaoguo0123/article/details/79293011](http://blog.csdn.net/kuangxiaoguo0123/article/details/79293011)
