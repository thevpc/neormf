using System;

namespace org.vpc.neormf.wfs.util
{
	/// <summary>
	/// Description résumée de TokenDesc.
	/// </summary>
	public class TokenDesc
	{
		private Type type;
		private int width;
		private String stringValue;

		public TokenDesc(Type type, int width)
		{
			this.type = type;
			this.width = width;
		}

		public string StringValue
		{
			get { return stringValue; }
			set
			{
				if(value==null)
				{
					stringValue = null;
					return;
				}
				if (type.Equals(typeof (int)))
				{
					Convert.ToInt32(value.Trim());
					stringValue = value.Trim();
				}
				else if (type.Equals(typeof (double)))
				{
					WFSUtils.ParseDouble(value.Trim());
					stringValue = value.Trim();
				}
				else 
				{
					stringValue = value==null ? null : value.Trim();
				}
			}
		}

		public int IntValue
		{
			get { return Convert.ToInt32(stringValue); }
		}

		public double DoubleValue
		{
			get
			{
				return WFSUtils.ParseDouble(stringValue.Trim());
			}
		}

		public Type Type
		{
			get { return type; }
		}

		public int Width
		{
			get { return width; }
		}
	}
}