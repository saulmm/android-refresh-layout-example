### Android refresh layout example

Proof of concept of the new feature _SwipeRefreshLayout_ included in the [Android Support Library, 19.1.0 review](http://developer.android.com/tools/support-library/index.html).

This class allows easily add a _pull to refresh_ feature in your apps, similar to implemented in some Googles applications such as Google Now.


![](https://googledrive.com/host/0B62SZ3WRM2R2WS1pRTN2aHpKR00)

For this you have to add the latest version of the library in your `build.gradle` in case we are developing with [Android Studio](http://developer.android.com/sdk/installing/studio.html), this should be enough for us with this line :

If you have not already _updated_ the _support library_, remember that you can do that using the __sdk manager__

![](https://googledrive.com/host/0B62SZ3WRM2R2S3hUWmtZb1hnRkE)

```
    compile 'com.android.support:support-v4:19.1.0'

```

As always , I recommend this wonderful resource : [_gradle please_](http://gradleplease.appspot.com/) by [Cristopher Broadfoot](https://plus.google.com/+ChristopherBroadfoot/posts) to find libraries if you are working with _gradle_ 

If you are working with eclipse, enough to download the latest version of the library in sdk manager, pick the library in your sdk path, in my case: `Applications/Android\ Studio.app/sdk/extras/android/support/v4/android-support-v4.jar` , add it to a folder `/libs` for example in your android project,



To use the feature `SwypeRefresh` , just have to put it as the root of our layout , for example:

```xml
<android.support.v4.widget.SwipeRefreshLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/swype"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <ListView
        android:id="@+id/list"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content" />

</android.support.v4.widget.SwipeRefreshLayout>
```

To get a callback  when we finished doing pull is necessary to implement the Listener in your activity,

```java
public class MainActivity extends Activity implements SwipeRefreshLayout.OnRefreshListener {
  
  ...
  
  @Override
  public void onRefresh() {
    ...
  }

```


You can put your swype layout color in your `colors.xml`

```xml
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <color name="swype_1">#ff92ff2f</color>
    <color name="swype_2">#ffffbc00</color>
    <color name="swype_3">#ff12ffa0</color>
    <color name="swype_4">#ffff6724</color>
</resources>
```

after that you can configure your colors with the `setColorScheme()` method that receives 4 color references:

```java
        layout = (SwipeRefreshLayout) findViewById(R.id.swype);

        // Set the refresh swype color scheme
        layout.setColorScheme(
                R.color.swype_1, R.color.swype_2,
                R.color.swype_3, R.color.swype_4);
```

In the callback handler implemented a delay to add to a small waiting time to enjoy the animation of the widget, after the time it disables the refresh

```java
    @Override
    public void onRefresh() {
        // I create a handler to stop the refresh and show a message after 3s
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                layout.setRefreshing(false);
                Toast.makeText(MainActivity.this, "Cool !", Toast.LENGTH_LONG).show();
            }

        }, 3000);
    }
```

And here the result:

![](https://googledrive.com/host/0B62SZ3WRM2R2dTBya0lLSmxjX2M)
