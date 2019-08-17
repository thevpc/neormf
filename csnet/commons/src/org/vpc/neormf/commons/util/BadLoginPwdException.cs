namespace org.vpc.neormf.util
{
	/// <summary>
	/// Description r�sum�e de UnauthorizedAccessException.
	/// </summary>
	public class BadLoginPwdException : BusinessException
	{
		public BadLoginPwdException() : base("Login ou mot de passe incorrect")
		{
		}
	}
}