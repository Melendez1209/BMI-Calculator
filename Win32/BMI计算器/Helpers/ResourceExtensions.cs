using Microsoft.Windows.ApplicationModel.Resources;

namespace BMI计算器.Helpers;

internal static class ResourceExtensions
{
    private static readonly ResourceLoader _resourceLoader = new();

    public static string GetLocalized(this string resourceKey) => _resourceLoader.GetString(resourceKey);
}
