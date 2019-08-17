using System;
using System.Collections;
using System.Globalization;
using System.Reflection;
using System.Web.UI;
using System.Web.UI.HtmlControls;
using System.Web.UI.WebControls;
using org.vpc.neormf.commons.beans;
using org.vpc.neormf.commons.types;
using org.vpc.neormf.commons.util;
using wws.src.org.vpc.neormf.wws;

namespace org.vpc.neormf.wfs.util
{
	/// <summary>
	/// Description résumée de WinDataHelper.
	/// </summary>
	public class UIDTO
	{
		private DataInfo info;
		private Hashtable comps=new Hashtable();
		private Hashtable labels=new Hashtable();
		System.Globalization.CultureInfo culture = null;

		public UIDTO(DataInfo info,Page page)
		{
			culture = new CultureInfo(page.Request.UserLanguages[0]);
			this.info=info;
			FieldInfo[] fi=page.GetType().GetFields();
			foreach (FieldInfo fieldInfo in fi)
			{
				if(fieldInfo.FieldType.IsAssignableFrom(typeof(Control)))
				{
					UIDTOField attr=(UIDTOField) Attribute.GetCustomAttribute(fieldInfo,typeof(UIDTOField));
					if(attr!=null)
					{
						InstallComponent((Control)fieldInfo.GetValue(page));
					}
				}
			}
		}
		
		public void InstallComponent(Control textBox)
		{
			InstallComponent(textBox,(Label)null);
		}

		public void InstallComponent(Control textBox,Label label)
		{
			InstallComponent(textBox,info.GetField(textBox.ID),label);
			comps.Add(textBox.ID,textBox);
			if(label!=null)
			{
				labels.Add(textBox.ID,label);
			}
		}


		public String StringValue(Control textBox)
		{
			return (String) StringToObject(textBox.ID,info.GetField(textBox.ID).getFieldType());
		}

		public int IntValue(Control textBox)
		{
			return (int) StringToObject(textBox.ID,info.GetField(textBox.ID).getFieldType());
		}
		
		public double DoubleValue(Control textBox)
		{
			return (double) StringToObject(textBox.ID,info.GetField(textBox.ID).getFieldType());
		}

		public Object StringToObject(Control textBox)
		{
			return StringToObject(textBox.ID,info.GetField(textBox.ID).getFieldType());
		}

		public String GetFieldTitle(Control textBox)
		{
			return info.GetField(textBox.ID).getFieldTitle();
		}

		private String GetControlText(Control c)
		{
			if(c is Label)
			{
				return ((Label)c).Text;
			}else if(c is TextBox)
			{
				return ((TextBox)c).Text;
			}else if(c is HtmlInputText)
			{
				return ((HtmlInputText)c).Value;
			}else if(c is HtmlInputHidden)
			{
				return ((HtmlInputHidden)c).Value;
			}else if(c is HtmlSelect)
			{
				return ((HtmlSelect)c).Value;
			}else if(c is DropDownList)
			{
				return ((DropDownList)c).SelectedValue;
			}else if(c is HtmlInputCheckBox)
			{
				return ((HtmlInputCheckBox)c).Value;
			}else if(c is HtmlInputRadioButton)
			{
				return ((HtmlInputRadioButton)c).Value;
			}
			return null;
		}

		private void SetControlText(Control c,String text)
		{
			if(c is Label)
			{
				((Label)c).Text=text;
			}
			else if(c is TextBox)
			{
				((TextBox)c).Text=text;
			}
			else if(c is HtmlInputText)
			{
				((HtmlInputText)c).Value=text;
			}
			else if(c is HtmlInputHidden)
			{
				((HtmlInputHidden)c).Value=text;
			}
			else if(c is HtmlInputRadioButton)
			{
				((HtmlInputRadioButton)c).Value=text;
			}
			else if(c is HtmlInputCheckBox)
			{
				((HtmlInputCheckBox)c).Value=text;
			}
			else if(c is HtmlSelect)
			{
				((HtmlSelect)c).Value=text;
			}
			else if(c is DropDownList)
			{
				((DropDownList)c).SelectedValue=text;
			}
		}

		private void SetControlTextLen(Control c,int len)
		{
			if(c is TextBox)
			{
				((TextBox)c).MaxLength=len;
			}
			else if(c is HtmlInputText)
			{
				((HtmlInputText)c).MaxLength=len;
			}
		}

		public Object GetFieldValue(String fieldName)
		{
			Control c=(Control)comps[fieldName];
			DataType dtype=info.GetField(fieldName).getFieldType();
			if(typeof(StringType).IsAssignableFrom(dtype.GetType()))
			{
				String txt=GetControlText(c);
				if(txt==null || txt.Length==0)
				{
					return null;
				}
				return txt;
			}
			else if(typeof(IntType).IsAssignableFrom(dtype.GetType()))
			{
				String txt=GetControlText(c);
				if(txt==null || txt.Length==0)
				{
					return null;
				}
				try
				{
					return Convert.ToInt32(txt);
				}
				catch (Exception e)
				{
					throw new DataException("org.vpc.neormf.commons.types.IntType.BadFormat.Message",new object[]{txt},e);
				}
			}
			else if(typeof(BooleanType).IsAssignableFrom(dtype.GetType()))
			{
				if(c is CheckBox)
				{
					return ((CheckBox)c).Checked;
				}else if(c is RadioButton)
				{
					return ((RadioButton)c).Checked;
				}else if(c is HtmlInputRadioButton)
				{
					return ((HtmlInputRadioButton)c).Checked;
				}else if(c is HtmlInputCheckBox)
				{
					return ((HtmlInputCheckBox)c).Checked;
				}else
				{
					throw new UnsupportedOperationException();
				}
			}
			else if(typeof(DoubleType).IsAssignableFrom(dtype.GetType()))
			{
				String txt=GetControlText(c);
				if(txt==null || txt.Length==0)
				{
					return null;
				}
				try
				{
					return Double.Parse(txt,culture.NumberFormat);
				}
				catch (Exception e)
				{
					throw new DataException("org.vpc.neormf.commons.types.DoubleType.BadFormat.Message",new object[]{txt},e);
				}
			}
			else if(typeof(DateTimeType).IsAssignableFrom(dtype.GetType()))
			{
				String txt=GetControlText(c);
				if(txt==null || txt.Length==0)
				{
					return null;
				}
				try
				{
					return Convert.ToDateTime(txt,culture.DateTimeFormat);
				}
				catch (Exception e)
				{
					throw new DataException("org.vpc.neormf.commons.types.DateTimeType.BadFormat.Message",new object[]{txt},e);
				}
			}
			else if(typeof(TimeType).IsAssignableFrom(dtype.GetType()))
			{
				String txt=GetControlText(c);
				if(txt==null || txt.Length==0)
				{
					return null;
				}
				try
				{
					return Convert.ToDateTime(txt,culture.DateTimeFormat).TimeOfDay;
				}
				catch (Exception e)
				{
					throw new DataException("org.vpc.neormf.commons.types.TimeType.BadFormat.Message",new object[]{txt},e);
				}
			}
			else 
			{
				throw new UnsupportedOperationException();
			}
		}

		public Object StringToObject(String txt,DataType dtype)
		{
			if(txt==null || txt.Length==0)
			{
				return null;
			}
			if(typeof(StringType).IsAssignableFrom(dtype.GetType()))
			{
				return txt;
			}
			else if(typeof(IntType).IsAssignableFrom(dtype.GetType()))
			{
				try
				{
					return Convert.ToInt32(txt);
				}
				catch (Exception e)
				{
					throw new DataException("org.vpc.neormf.commons.types.IntType.BadFormat.Message",new object[]{txt},e);
				}
			}
			else if(typeof(DoubleType).IsAssignableFrom(dtype.GetType()))
			{
				try
				{
					return 	Double.Parse(txt,culture.NumberFormat);
				}
				catch (Exception e)
				{
					throw new DataException("org.vpc.neormf.commons.types.DoubleType.BadFormat.Message",new object[]{txt},e);
				}
			}
			else if(typeof(TimeType).IsAssignableFrom(dtype.GetType()))
			{
				try
				{
					return Convert.ToDateTime(txt,culture.DateTimeFormat).TimeOfDay;
				}
				catch (Exception e)
				{
					throw new DataException("org.vpc.neormf.commons.types.TimeType.BadFormat.Message",new object[]{txt},e);
				}
			}
			else if(typeof(DateTimeType).IsAssignableFrom(dtype.GetType()))
			{
				try
				{
					return Convert.ToDateTime(txt,culture.DateTimeFormat);
				}catch(Exception e)
				{
					throw new DataException("org.vpc.neormf.commons.types.DateTimeType.BadFormat.Message",new object[]{txt},e);
				}
			}
			else 
			{
				throw new UnsupportedOperationException();
			}
		}

		public String ObjectToString(Object o,DataType dtype)
		{
			if(o==null)
			{
				return "";
			}
			if(typeof(DoubleType).IsAssignableFrom(dtype.GetType()))
			{
				return Convert.ToString((double)o,culture.NumberFormat);
			}
			return Convert.ToString(o);
		}

		public void UpdateUI(DataContent dc)
		{
			UpdateUI(dc,null,null,null);
		}

		public void SetFieldValue(String name,Object o)
		{
			Control textBox=(Control)comps[name];
			if(textBox is CheckBox)
			{
				((CheckBox)textBox).Checked= (o!=null && (o!=null && (bool)o));
			}
			SetControlText(textBox,ObjectToString(o,info.GetField(textBox.ID).getFieldType()));
		}

		public void UpdateUI(DataContent dc,PropertyList includeList,PropertyList excludeList,PropertyList ignoreIfEmptyList)
		{
			ArrayList theList=includeList==null?new ArrayList(comps.Keys) : new ArrayList(includeList.KeySet()); 
			foreach(String k in  theList)
			{
				Control textBox=(Control)comps[k];
				bool doExcludeThis=false;
				if(excludeList!=null && excludeList.ContainsProperty(textBox.ID))
				{
					doExcludeThis=true;
				}
				if(!doExcludeThis)
				{
					try
					{
						if(!doExcludeThis)
						{
							if(ignoreIfEmptyList!=null && ignoreIfEmptyList.ContainsProperty(textBox.ID))
							{
								doExcludeThis=true;
							}
						}
						if(!doExcludeThis)
						{
							SetFieldValue(k,dc.GetProperty(k));
						}
					}
					catch(Exception exc)
					{
						SetControlText(textBox,"");
						throw exc;
					}
				}
			}
		}

		public void UpdateDTO(DataContent dc)
		{
			UpdateDTO(dc,null,null,null);
		}

		public void UpdateDTO(DataContent dc,PropertyList includeList,PropertyList excludeList,PropertyList ignoreIfEmptyList)
		{
			ArrayList theList=includeList==null?new ArrayList(comps.Keys) : new ArrayList(includeList.KeySet()); 
			foreach(String k in  theList)
			{
				Control textBox=(Control)comps[k];
				bool doExcludeThis=false;
				if(excludeList!=null && excludeList.ContainsProperty(textBox.ID))
				{
					doExcludeThis=true;
				}
				if(!doExcludeThis)
				{
					try
					{
						Object o=GetFieldValue(k);
						if(!doExcludeThis && o==null)
						{
							if(ignoreIfEmptyList!=null && ignoreIfEmptyList.ContainsProperty(textBox.ID))
							{
								doExcludeThis=true;
							}
						}
						if(!doExcludeThis)
						{
							dc.SetProperty(k,o);
						}
					}catch(Exception exc)
					{
						SetControlText(textBox,"");
						throw exc;
					}
				}
			}
		}
		
		public void UpdateDTO(DataContent dc,String name,Object value)
		{
			Control textBox=(Control)comps[name];
			try
			{
				dc.SetProperty(name,value);
			}catch(Exception exc)
			{
				SetControlText(textBox,"");
				throw exc;
			}
		}

		public void InstallComponent(Control textBox,DataInfo info,Label label)
		{
			InstallComponent(textBox,info.GetField(textBox.ID),label);
		}

		public void InstallComponent(Control textBox,DataType dtype)
		{
			if(typeof(StringType).IsAssignableFrom(dtype.GetType()))
			{
				StringType itype=(StringType)dtype;
				int max=itype.GetMaxLength();
				SetControlTextLen(textBox,max);
				return;
			}
			else if(typeof(IntType).IsAssignableFrom(dtype.GetType()))
			{
				IntType itype=(IntType)dtype;
				SetControlTextLen(textBox,(int)Math.Ceiling(Math.Log10(itype.GetMaxValue())));
				return;
			}
			else if(typeof(DoubleType).IsAssignableFrom(dtype.GetType()))
			{
				//DoubleType dbltype=(DoubleType)dtype;
				SetControlTextLen(textBox,50);
				//textBox.KeyPress+=new KeyPressEventHandler(DoubleTextBox_KeyPress);
				return;
			}
			else if(typeof(DateTimeType).IsAssignableFrom(dtype.GetType()))
			{
				return;
			}
			else if(typeof(TimeType).IsAssignableFrom(dtype.GetType()))
			{
				return;
			}
			else if(typeof(BooleanType).IsAssignableFrom(dtype.GetType()))
			{
				return;
			}
			throw new UnsupportedOperationException();
		}

		public void InstallComponent(Control textBox,DataField f,Label label)
		{
			if(label!=null)
			{
				label.Text=f.getFieldTitle();
			}
			DataType dtype=f.getFieldType();
			InstallComponent(textBox,dtype);
		}

		public CultureInfo Culture
		{
			get { return culture; }
			set { culture = value; }
		}
	}
}
