using System;
using System.Collections;
/**
 * Created by IntelliJ IDEA.
 * User: vpc
 * Date: 12 août 2005
 * Time: 14:55:47
 * To change this template use File | Settings | File Templates.
 */

namespace org.vpc.neormf.commons.types
{
	public class ListChoiceType : AbstractChoiceType
	{
		private IList items;
		private IList renderers;
		private IList mappedElements;
		private Hashtable hashtable;

		public ListChoiceType(bool nullable, Object[] elements, DataType elementType) : this(nullable, new ArrayList(elements), elementType)
		{
		}

		public ListChoiceType(bool nullable, Object[] elements, Object[] renderers, DataType elementType) : this(nullable, new ArrayList(elements),new ArrayList(renderers), elementType)
		{
		}

		public ListChoiceType(bool nullable, IList elements, DataType elementType) : this(nullable, elements,elements,elementType)
		{
		}

		public ListChoiceType(bool nullable, IList elements, IList  renderers, DataType elementType) : base(nullable, elementType)
		{
			this.items = elements;
			this.renderers = renderers;
			mappedElements=new ArrayList();
			hashtable=new Hashtable();
			for(int i=0;i<elements.Count;i++)
			{
				hashtable[elements[i]]=(renderers[i]==null?"":Convert.ToString(renderers[i]));
				mappedElements.Add(new Element(elements[i],renderers[i]==null?"":Convert.ToString(renderers[i])));
			}
		}

		public override Object ValidateValue(Object obj, String fieldName)
		{
			obj = base.ValidateValue(obj, fieldName);
			if (obj != null)
			{
				if (!items.Contains(obj))
				{
					obj = null;
				}
			}
			return obj;
		}

		public override String GetKeyRenderer(Object value)
		{
			return (String)hashtable[value];
		}

		public override IList Elements()
		{
			return mappedElements;
		}
		
		public IList Renderers(){
			return renderers;
		}

	}
}