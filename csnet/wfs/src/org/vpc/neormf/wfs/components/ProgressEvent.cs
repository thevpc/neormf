using System;

namespace org.vpc.neormf.wfs.util
{
	/// <summary>
	/// Description résumée de ProgressEvent.
	/// </summary>
	public class ProgressEvent
	{
		private ProgressEvent parentEvent;
		private int index;
		private int max;
		private String messageType;
		private String message;

		public ProgressEvent(ProgressEvent parentEvent, int index, int max, string messageType, string message)
		{
			this.index = index;
			this.max = max;
			this.messageType = messageType;
			this.message = message;
			this.parentEvent = parentEvent;
		}

		public int Index
		{
			get { return index; }
		}

		public double IndexFraction
		{
			get
			{
				if (parentEvent == null)
				{
					return max <= 0 ? -1.0 : index < 0 ? -1.0 : (((double) index)/max);
				}
				if (max <= 0 || index < 0)
				{
					return parentEvent.IndexFraction;
				}

				double d = ((((double) index)/max) + parentEvent.index)/parentEvent.max;
				return d;
			}
		}

		public int Max
		{
			get { return max; }
		}

		public string MessageType
		{
			get { return messageType; }
		}

		public string Message
		{
			get { return message; }
		}

		public ProgressEvent ParentEvent
		{
			get { return parentEvent; }
		}
	}
}