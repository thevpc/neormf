using System;
using System.Xml;

namespace org.vpc.neormf.commons.util
{
	public class XmlUtils
	{
		private XmlUtils()
		{
		}

		public static XmlElement AppendCDataNode(XmlNode parentNode, String name, String value)
		{
			XmlElement title = parentNode.OwnerDocument.CreateElement(name);
			title.AppendChild(parentNode.OwnerDocument.CreateCDataSection(ToEscapeString(value)));
			parentNode.AppendChild(title);
			return title;
		}

		public static XmlElement AppendNonNullTextNode(XmlNode parentNode, String name, String value)
		{
			if(value!=null && value.Length>0)
			{
				XmlElement title = parentNode.OwnerDocument.CreateElement(name);
				title.AppendChild(parentNode.OwnerDocument.CreateTextNode(ToEscapeString(value)));
				parentNode.AppendChild(title);
				return title;
			}else
			{
				return null;
			}
		}

		public static void AppendTextNode(XmlNode parentNode, String name, String value)
		{
			XmlElement title = parentNode.OwnerDocument.CreateElement(name);
			title.AppendChild(parentNode.OwnerDocument.CreateTextNode(ToEscapeString(value)));
			parentNode.AppendChild(title);
		}

//		public static void AppendDateTimeNode(XmlNode parentNode, String name, DateTime value)
//		{
//			XmlElement title = parentNode.OwnerDocument.CreateElement(name);
//			title.AppendChild(parentNode.OwnerDocument.CreateTextNode(Utils.ConvertDateTimeToString(value)));
//			parentNode.AppendChild(title);
//		}
		private static char ESCAPE='#';
		public static void AppendAttribute(XmlNode parentNode, String name, String value)
		{
			XmlAttribute versionAttrib = parentNode.OwnerDocument.CreateAttribute(name);
			versionAttrib.Value = ToEscapeString(value);
			parentNode.Attributes.Append(versionAttrib);
		}

		public static string ToEscapeString(String plainString)
		{
			string b="";
			if(plainString!=null)
			{
				for(int i=0;i<plainString.Length;i++)
				{
					char c=plainString[i];
					if(c==ESCAPE)
					{
						b+=@ESCAPE;
					}else if(c>=32 && c<=150)
					{
						b+=c;
					}else
					{
						b+=ESCAPE+@"u";
						if(c<10)
						{
							b+="00";
						}else if(c<100)
						{
							b+="0";
						}
						b+=((int)c);
					}
				}
			}
			return b;
		}

		public static string GetNodeText(XmlNode node)
		{
			return FromEscapeString(node.InnerText);
		}

		public static string FromEscapeString(string excapedString)
		{
			string b="";
			if(excapedString!=null)
			{
				for(int i=0;i<excapedString.Length;i++)
				{
					char c=excapedString[i];
					if(c==ESCAPE)
					{
						i++;
						c=excapedString[i];
						if(c=='u')
						{
							int code=int.Parse(excapedString.Substring(i+1,3));
							b+=((char)code);
							i+=3;
						}else if(c==ESCAPE)
						{
							b+=ESCAPE;
						}else
						{
							throw new ArgumentException("unexpected escape sequence "+ESCAPE+c);
						}
					}else
					{
						b+=c;
					}

				}
			}
			return b;
		}
	}
}