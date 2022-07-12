using System.Threading.Tasks;

namespace BMI计算器.Contracts.Services;

public interface IActivationService
{
    Task ActivateAsync(object activationArgs);
}
