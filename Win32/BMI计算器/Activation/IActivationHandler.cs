using System.Threading.Tasks;

namespace BMI计算器.Activation;

public interface IActivationHandler
{
    bool CanHandle(object args);

    Task HandleAsync(object args);
}
