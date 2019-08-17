using System;
using System.Collections;
using System.Windows.Forms;
using org.vpc.neormf.commons.beans;
using org.vpc.neormf.commons.types;
using org.vpc.neormf.commons.util;

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
		System.Globalization.NumberFormatInfo ni = null;

		public UIDTO(DataInfo info)
		{
			System.Globalization.CultureInfo ci = System.Globalization.CultureInfo.InstalledUICulture;
			ni = (System.Globalization.NumberFormatInfo) ci.NumberFormat.Clone();
			//ni.NumberDecimalSeparator = ".";
			this.info=info;
		}
		
		public void InstallComponent(Control textBox)
		{
			InstallComponent(textBox,(Label)null);
		}

		public void InstallComponent(Control textBox,Label label)
		{
			InstallComponent(textBox,info.GetField(textBox.Name),label);
			comps.Add(textBox.Name,textBox);
			if(label!=null)
			{
				labels.Add(textBox.Name,label);
			}
		}


		public String StringValue(Control textBox)
		{
			return (String) StringToObject(textBox.Text,info.GetField(textBox.Name).getFieldType());
		}

		public int IntValue(Control textBox)
		{
			return (int) StringToObject(textBox.Text,info.GetField(textBox.Name).getFieldType());
		}
		
		public double DoubleValue(Control textBox)
		{
			return (double) StringToObject(textBox.Text,info.GetField(textBox.Name).getFieldType());
		}

		public Object StringToObject(Control textBox)
		{
			return StringToObject(textBox.Text,info.GetField(textBox.Name).getFieldType());
		}

		public String GetFieldTitle(Control textBox)
		{
			return info.GetField(textBox.Name).getFieldTitle();
		}

		public Object GetFieldValue(String fieldName)
		{
			Control c=(Control)comps[fieldName];
			DataType dtype=info.GetField(fieldName).getFieldType();
			if(typeof(StringType).IsAssignableFrom(dtype.GetType()))
			{
				String txt=c.Text;
				if(txt==null || txt.Length==0)
				{
					return null;
				}
				return txt;
			}
			else if(typeof(IntType).IsAssignableFrom(dtype.GetType()))
			{
				String txt=c.Text;
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
				}else
				{
					throw new UnsupportedOperationException();
				}
			}
			else if(typeof(DoubleType).IsAssignableFrom(dtype.GetType()))
			{
				String txt=c.Text;
				if(txt==null || txt.Length==0)
				{
					return null;
				}
				try
				{
					return Double.Parse(txt,ni);
				}
				catch (Exception e)
				{
					throw new DataException("org.vpc.neormf.commons.types.DoubleType.BadFormat.Message",new object[]{txt},e);
				}
			}
			else if(typeof(DateTime).IsAssignableFrom(dtype.GetType()))
			{
				String txt=c.Text;
				if(txt==null || txt.Length==0)
				{
					return null;
				}
				try
				{
					return Convert.ToDateTime(txt);
				}
				catch (Exception e)
				{
					throw new DataException("org.vpc.neormf.commons.types.DateTimeType.BadFormat.Message",new object[]{txt},e);
				}
			}
			else if(typeof(TimeType).IsAssignableFrom(dtype.GetType()))
			{
				String txt=c.Text;
				if(txt==null || txt.Length==0)
				{
					return null;
				}
				try
				{
					return Convert.ToDateTime(txt).TimeOfDay;
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
					return 	Double.Parse(txt,ni);
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
					return Convert.ToDateTime(txt).TimeOfDay;
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
					return Convert.ToDateTime(txt);
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
				return Convert.ToString((double)o,ni);
			}
			return Convert.ToString(o);
		}

		public void UpdateGUI(DataContent dc)
		{
			UpdateGUI(dc,null,null,null);
		}

		public void SetFieldValue(String name,Object o)
		{
			Control textBox=(Control)comps[name];
			if(textBox is TextBox)
			{
				textBox.Text=ObjectToString(o,info.GetField(textBox.Name).getFieldType());
			}else if(textBox is CheckBox)
			{
				((CheckBox)textBox).Checked= (o!=null && (o!=null && (bool)o));
			}
		}

		public void UpdateGUI(DataContent dc,PropertyList includeList,PropertyList excludeList,PropertyList ignoreIfEmptyList)
		{
			ArrayList theList=includeList==null?new ArrayList(comps.Keys) : new ArrayList(includeList.KeySet()); 
			foreach(String k in  theList)
			{
				Control textBox=(Control)comps[k];
				bool doExcludeThis=false;
				if(excludeList!=null && excludeList.ContainsProperty(textBox.Name))
				{
					doExcludeThis=true;
				}
				if(!doExcludeThis)
				{
					try
					{
						if(!doExcludeThis)
						{
							if(ignoreIfEmptyList!=null && ignoreIfEmptyList.ContainsProperty(textBox.Name))
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
						if(textBox is TextBox)
						{
							textBox.Text="";
							textBox.Focus();
						}
						throw exc;
					}
				}
			}
		}

		public void UpdateData(DataContent dc)
		{
			UpdateData(dc,null,null,null);
		}

		public void UpdateData(DataContent dc,PropertyList includeList,PropertyList excludeList,PropertyList ignoreIfEmptyList)
		{
			ArrayList theList=includeList==null?new ArrayList(comps.Keys) : new ArrayList(includeList.KeySet()); 
			foreach(String k in  theList)
			{
				Control textBox=(Control)comps[k];
				bool doExcludeThis=false;
				if(excludeList!=null && excludeList.ContainsProperty(textBox.Name))
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
							if(ignoreIfEmptyList!=null && ignoreIfEmptyList.ContainsProperty(textBox.Name))
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
						if(textBox is TextBox)
						{
							textBox.Text="";
							textBox.Focus();
						}
						throw exc;
					}
				}
			}
		}
		
		public void UpdateData(DataContent dc,String name,Object value)
		{
			Control textBox=(Control)comps[name];
			try
			{
				dc.SetProperty(name,value);
			}catch(Exception exc)
			{
				if(textBox is TextBox)
				{
					textBox.Text="";
					textBox.Focus();
				}
				throw exc;
			}
		}

		public void InstallComponent(Control textBox,DataInfo info,Label label)
		{
			InstallComponent(textBox,info.GetField(textBox.Name),label);
		}

		public void InstallComponent(Control textBox,DataType dtype)
		{
			if(typeof(StringType).IsAssignableFrom(dtype.GetType()))
			{
				StringType itype=(StringType)dtype;
				int max=itype.GetMaxLength();
				if(textBox is TextBox)
				{
					((TextBox)textBox).MaxLength=max;
					return;
				}
			}
			else if(typeof(IntType).IsAssignableFrom(dtype.GetType()))
			{
				if(textBox is TextBox)
				{
					IntType itype=(IntType)dtype;
					((TextBox)textBox).MaxLength=(int)Math.Ceiling(Math.Log10(itype.GetMaxValue()));
					textBox.KeyPress+=new KeyPressEventHandler(IntTextBox_KeyPress);
					return;
				}
			}
			else if(typeof(DoubleType).IsAssignableFrom(dtype.GetType()))
			{
				if(textBox is TextBox)
				{
					//DoubleType dbltype=(DoubleType)dtype;
					((TextBox)textBox).MaxLength=50;
					textBox.KeyPress+=new KeyPressEventHandler(DoubleTextBox_KeyPress);
					return;
				}
			}
			else if(typeof(DateTimeType).IsAssignableFrom(dtype.GetType()))
			{
				if(textBox is TextBox)
				{
					return;
				}
			}
			else if(typeof(TimeType).IsAssignableFrom(dtype.GetType()))
			{
				if(textBox is TextBox)
				{
					return;
				}
			}
			else if(typeof(BooleanType).IsAssignableFrom(dtype.GetType()))
			{
				if((textBox is CheckBox) || (textBox is RadioButton))
				{
					return;
				}
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

		private void IntTextBox_KeyPress(object sender,KeyPressEventArgs kpe)
		{
			if(!Char.IsDigit(kpe.KeyChar) && kpe.KeyChar!=8)
			{
				kpe.Handled=true;
			}
		}

		private void DoubleTextBox_KeyPress(object sender,KeyPressEventArgs kpe)
		{
			if(!Char.IsDigit(kpe.KeyChar) && kpe.KeyChar!=ni.NumberDecimalSeparator[0]  && kpe.KeyChar!=8)
			{
				kpe.Handled=true;
			}
		}

	}
}
