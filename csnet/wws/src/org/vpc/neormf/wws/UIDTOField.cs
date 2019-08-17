using System;

namespace wws.src.org.vpc.neormf.wws
{
	[AttributeUsage(AttributeTargets.Field,Inherited=true,AllowMultiple=false)]
	public class UIDTOField:Attribute
	{
		public UIDTOField()
		{
		}
	}
}
