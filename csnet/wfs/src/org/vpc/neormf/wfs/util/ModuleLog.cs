using System;
using log4net;
using log4net.spi;

namespace org.vpc.neormf.wfs.util
{
	/// <summary>
	/// Description résumée de Log2.
	/// </summary>
	public class ModuleLog:ILog
	{
		private String module;
		private ILog log;

		public ModuleLog(string module, ILog log)
		{
			this.module = module;
			this.log = log;
		}

		public String BuildMessage(Object msg)
		{
			return "["+module+"] ["+WFSApplication.Current.CallerPrincipalName+"] "+msg;
		}

		public void Debug(object message)
		{
			log.Debug(BuildMessage(message));
		}

		public void Debug(object message, Exception t)
		{
			log.Debug(BuildMessage(message), t);
		}

		public void Info(object message)
		{
			log.Info(BuildMessage(message));
		}

		public void Info(object message, Exception t)
		{
			log.Info(BuildMessage(message), t);
		}

		public void Warn(object message)
		{
			log.Warn(BuildMessage(message));
		}

		public void Warn(object message, Exception t)
		{
			log.Warn(BuildMessage(message), t);
		}

		public void Error(object message)
		{
			log.Error(BuildMessage(message));
		}

		public void Error(object message, Exception t)
		{
			log.Error(BuildMessage(message), t);
		}

		public void Fatal(object message)
		{
			log.Fatal(BuildMessage(message));
		}

		public void Fatal(object message, Exception t)
		{
			log.Fatal(BuildMessage(message),t);
		}

		public bool IsDebugEnabled
		{
			get { return log.IsDebugEnabled; }
		}

		public bool IsInfoEnabled
		{
			get { return log.IsInfoEnabled; }
		}

		public bool IsWarnEnabled
		{
			get { return log.IsWarnEnabled; }
		}

		public bool IsErrorEnabled
		{
			get { return log.IsErrorEnabled; }
		}

		public bool IsFatalEnabled
		{
			get { return  log.IsFatalEnabled;}
		}

		public ILogger Logger
		{
			get { return log.Logger; }
		}
	}
}
