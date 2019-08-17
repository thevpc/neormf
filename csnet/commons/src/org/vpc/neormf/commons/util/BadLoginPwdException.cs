namespace org.vpc.neormf.util
{
	/// <summary>
	/// Description résumée de UnauthorizedAccessException.
	/// </summary>
	public class BadLoginPwdException : BusinessException
	{
		public BadLoginPwdException() : base("Login ou mot de passe incorrect")
		{
		}
	}
}