using System;
using System.Collections;
using System.Text;
/**
 * class presentation
 *
 * @author taha BEN SALAH (tbensalah)
 * @version 1.0
 * @copyrights (c) 2004, Vpc Open Source Foundary
 * @project New Entreprise Object Relational Mapping Framework (neormf)
 * @creation on Date: 1 avr. 2004 Time: 22:26:31
 * @modification on ---- by -----
 * @modification on ---- by -----
 * @modification on ---- by -----
 */

namespace org.vpc.neormf.commons.types
{
	public abstract class AbstractChoiceType : DataType
	{
		public static int TYPE = -2;
		private DataType elementType;

		public AbstractChoiceType(bool nullable, DataType elementType):base(nullable)
		{
			this.elementType = elementType;
		}

		public override Object ValidateValue(Object obj, String fieldName)
		{
			return elementType.ValidateValue(obj, fieldName);
		}

		public override Type ToImplType()
		{
			return elementType.ToImplType();
		}


		public DataType GetElementType()
		{
			return elementType;
		}

		/**
	 *
	 * @return list of Element
	 */
		public abstract IList Elements();

		public abstract String GetKeyRenderer(Object key);

		public override int GetTypeId()
		{
			return TYPE;
		}


	}

	public class Element
	{
		private Object key;
		private String renderer;

		public Element(Object key, String value)
		{
			this.key = key;
			this.renderer = value;
		}

		public Object getKey()
		{
			return key;
		}

		public String getRenderer()
		{
			return renderer;
		}
	}
}