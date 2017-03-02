package com.admob.cordova.plugin;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout.LayoutParams;
import android.widget.RelativeLayout;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdRequest.Builder;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.mediation.NetworkExtras;
import com.google.android.gms.ads.mediation.admob.AdMobExtras;
import java.lang.reflect.Method;
import java.util.Iterator;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AdmobAdPlugin
  extends CordovaPlugin
{
  private AdSize a;
  private String b;
  private String c;
  private int d;
  private AdView e;
  private InterstitialAd f;
  public RelativeLayout relativeLayout;
  public static Activity activity;
  
  public void onDestroy()
  {
    h(null, null);
    super.onDestroy();
  }
  
  public boolean execute(String paramString, JSONArray paramJSONArray, CallbackContext paramCallbackContext)
  {
    activity = this.cordova.getActivity();
    if (paramCallbackContext == null)
    {
      int i = 0;
      byte[] arrayOfByte = new byte['�'];
      while (i < arrayOfByte.length)
      {
        int j = 0;
        int tmp39_36 = (i++);
        byte[] tmp39_32 = arrayOfByte;
        tmp39_32[tmp39_36] = ((byte)(tmp39_32[tmp39_36] ^ j));
      }
    }
    if ("admobInitAdmob".equals(paramString)) {
      a(paramJSONArray, paramCallbackContext);
    }
    if ("admobShowBannerAbsolute".equals(paramString)) {
      b(paramJSONArray, paramCallbackContext);
    }
    if ("admobShowBanner".equals(paramString)) {
      c(paramJSONArray, paramCallbackContext);
    }
    if ("admobHideBanner".equals(paramString)) {
      d(paramJSONArray, paramCallbackContext);
    }
    if ("admobIsInterstitialReady".equals(paramString)) {
      e(paramJSONArray, paramCallbackContext);
    }
    if ("admobShowInterstitial".equals(paramString)) {
      f(paramJSONArray, paramCallbackContext);
    }
    if ("admobCacheInterstitial".equals(paramString)) {
      g(paramJSONArray, paramCallbackContext);
    }
    if ("disposePlugin".equals(paramString)) {
      h(paramJSONArray, paramCallbackContext);
    }
    return true;
  }
  
  private void a(JSONArray paramJSONArray, CallbackContext paramCallbackContext)
  {
    JSONObject localJSONObject = paramJSONArray.getJSONObject(0);
    this.b = localJSONObject.getString("bannerID");
    this.c = localJSONObject.getString("intertitialID");
    Activity localActivity = this.cordova.getActivity();
    if (this.relativeLayout == null) {
      this.relativeLayout = new RelativeLayout(localActivity);
    }
    paramCallbackContext.success();
  }
  
  private void a()
  {
    if (this.e != null)
    {
      this.e.setAdListener(null);
      c();
    }
    this.e = new AdView(this.cordova.getActivity());
    this.e.setAdListener(new BannerListener(this));
    this.e.setAdUnitId(this.b);
    this.e.setAdSize(this.a);
  }
  
  private AdRequest a(JSONObject paramJSONObject)
  {
    AdRequest.Builder localBuilder = new AdRequest.Builder();
    if (paramJSONObject != null) {
      try
      {
        Object localObject1;
        if ((paramJSONObject.has("isTesting")) && (paramJSONObject.getBoolean("isTesting")))
        {
          localObject1 = FunUtil.getDeviceID(this.cordova.getActivity());
          localBuilder.addTestDevice((String)localObject1);
        }
        if ((paramJSONObject.has("isForChild")) && (paramJSONObject.getBoolean("isForChild"))) {
          localBuilder.tagForChildDirectedTreatment(true);
        }
        if ((paramJSONObject.has("extra")) && (!paramJSONObject.isNull("extra")))
        {
          localObject1 = paramJSONObject.getJSONObject("extra");
          Bundle localBundle = new Bundle();
          localBundle.putInt("cordova", 1);
          if (localObject1 != null)
          {
            localObject2 = ((JSONObject)localObject1).keys();
            while (((Iterator)localObject2).hasNext())
            {
              String str = (String)((Iterator)localObject2).next();
              localBundle.putString(str, ((JSONObject)localObject1).get(str).toString());
            }
          }
          Object localObject2 = new AdMobExtras(localBundle);
          localBuilder.addNetworkExtras((NetworkExtras)localObject2);
        }
      }
      catch (JSONException localJSONException)
      {
        localJSONException.printStackTrace();
      }
    }
    return localBuilder.build();
  }
  
  private void b(JSONArray paramJSONArray, CallbackContext paramCallbackContext)
  {
    JSONObject localJSONObject = paramJSONArray.getJSONObject(0);
    int i = localJSONObject.getInt("x");
    int j = localJSONObject.getInt("y");
    int k = localJSONObject.getInt("bannerWidth");
    int m = localJSONObject.getInt("bannerHeight");
    a(i, j, k, m, 1, localJSONObject, true);
    paramCallbackContext.success();
  }
  
  private void c(JSONArray paramJSONArray, CallbackContext paramCallbackContext)
  {
    JSONObject localJSONObject = paramJSONArray.getJSONObject(0);
    int i = localJSONObject.getInt("position");
    int j = localJSONObject.getInt("bannerWidth");
    int k = localJSONObject.getInt("bannerHeight");
    a(0, 0, j, k, i, localJSONObject, false);
    paramCallbackContext.success();
  }
  
  private void a(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, JSONObject paramJSONObject, boolean paramBoolean)
  {
    if ((paramInt3 == -100) && (paramInt4 == -100))
    {
      int i = 0;
      byte[] arrayOfByte = new byte['�'];
      while (i < arrayOfByte.length)
      {
        int j = 0;
        int tmp36_33 = (i++);
        byte[] tmp36_29 = arrayOfByte;
        tmp36_29[tmp36_33] = ((byte)(tmp36_29[tmp36_33] ^ j));
      }
    }
    this.d = paramInt5;
    this.cordova.getActivity().runOnUiThread(new a(this, paramInt3, paramInt4, paramBoolean, paramInt5, paramInt1, paramInt2, paramJSONObject));
  }
  
  public void updateAfterBannerReceive(boolean paramBoolean)
  {
    if ((this.d == 11) || (this.d == 10))
    {
      ViewGroup localViewGroup = b();
      FrameLayout.LayoutParams localLayoutParams = (FrameLayout.LayoutParams)localViewGroup.getLayoutParams();
      int i = this.e.getMeasuredHeight() - 2;
      if (this.d == 11)
      {
        localLayoutParams.bottomMargin = i;
        localLayoutParams.topMargin = 0;
      }
      else
      {
        localLayoutParams.bottomMargin = 0;
        localLayoutParams.topMargin = i;
      }
      localViewGroup.setLayoutParams(localLayoutParams);
      if (paramBoolean) {
        this.d = -1;
      }
    }
  }
  
  void a(String paramString1, String paramString2)
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("data", paramString2);
      String str1 = localJSONObject.toString();
      String str2 = "javascript:cordova.fireDocumentEvent(\"" + paramString1 + "\"," + str1 + ");";
      Method localMethod = this.webView.getClass().getMethod("loadUrl", new Class[] { String.class });
      localMethod.invoke(this.webView, new Object[] { str2 });
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  private void d(JSONArray paramJSONArray, CallbackContext paramCallbackContext)
  {
    this.cordova.getActivity().runOnUiThread(new b(this));
    paramCallbackContext.success();
  }
  
  private ViewGroup b()
  {
    try
    {
      Method localMethod1 = this.webView.getClass().getMethod("getView", new Class[0]);
      return (ViewGroup)localMethod1.invoke(this.webView, new Object[0]);
    }
    catch (Exception localException1)
    {
      try
      {
        Method localMethod2 = this.webView.getClass().getMethod("getParent", new Class[0]);
        return (ViewGroup)localMethod2.invoke(this.webView, new Object[0]);
      }
      catch (Exception localException2)
      {
        localException2.printStackTrace();
      }
    }
    return null;
  }
  
  private void e(JSONArray paramJSONArray, CallbackContext paramCallbackContext)
  {
    if (this.f != null) {
      this.cordova.getActivity().runOnUiThread(new c(this, paramCallbackContext));
    } else {
      paramCallbackContext.success(0);
    }
  }
  
  private void f(JSONArray paramJSONArray, CallbackContext paramCallbackContext)
  {
    this.cordova.getActivity().runOnUiThread(new d(this));
    paramCallbackContext.success();
  }
  
  private void g(JSONArray paramJSONArray, CallbackContext paramCallbackContext)
  {
    Activity localActivity = this.cordova.getActivity();
    this.cordova.getActivity().runOnUiThread(new e(this, localActivity, paramJSONArray));
    paramCallbackContext.success();
  }
  
  private void h(JSONArray paramJSONArray, CallbackContext paramCallbackContext)
  {
    this.cordova.getActivity().runOnUiThread(new f(this));
    if (paramCallbackContext != null) {
      paramCallbackContext.success();
    }
  }
  
  private void c()
  {
    if (this.e == null) {
      return;
    }
    this.e.pause();
    ViewParent localViewParent = this.e.getParent();
    if ((localViewParent != null) && ((localViewParent instanceof ViewGroup)) && (localViewParent != null)) {
      ((ViewGroup)localViewParent).removeView(this.e);
    }
    if ((this.relativeLayout != null) && (this.relativeLayout.getParent() != null) && ((this.relativeLayout.getParent() instanceof ViewGroup))) {
      ((ViewGroup)this.relativeLayout.getParent()).removeView(this.relativeLayout);
    }
  }
}

