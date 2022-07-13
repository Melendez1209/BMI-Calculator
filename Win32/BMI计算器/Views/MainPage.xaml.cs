using BMI计算器.ViewModels;
using System;
using Microsoft.UI.Xaml.Controls;

namespace BMI计算器.Views;

public sealed partial class MainPage : Page
{
    //全局变量
    public const string TAG = "Melendez_ainPage";

    public string BMI;

    private MainViewModel ViewModel
    {
        get;
    }

    public MainPage()
    {
        ViewModel = App.GetService<MainViewModel>();
        InitializeComponent();
    }

    public void log(string node)
    {
        Console.WriteLine(TAG + node);
    }

    private void SettingLink(object sender, Microsoft.UI.Xaml.RoutedEventArgs e)
    {
        log("跳转至设置");
        this.Frame.Navigate(typeof(SettingsPage));
    }

    private void Calculation(object sender, Microsoft.UI.Xaml.RoutedEventArgs e)
    {
        log("获取数据并转换");
        //获取数据并转换
        var height = (float)Height.Value;
        var weight = (float)Weight.Value;
        double BMI_double = weight / (height * height);
        BMI = BMI_double.ToString("f2");//保留两位小数
        log("BMI计算完成");
        Show.Subtitle = "您的BMI计算结果约为：" + BMI;
        Show.IsOpen = true;
    }

    private void Show_ActionButtonClick(TeachingTip sender, object args)
    {
        log("调整至结果页");
        this.Frame.Navigate(typeof(ResultPage), BMI);
    }
}