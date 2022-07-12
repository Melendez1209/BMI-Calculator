using System;

namespace BMI计算器.Contracts.Services;

public interface IPageService
{
    Type GetPageType(string key);
}
