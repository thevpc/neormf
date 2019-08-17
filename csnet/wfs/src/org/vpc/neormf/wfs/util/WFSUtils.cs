using System;
using System.Collections;
using System.Data;
using System.Reflection;
using log4net;
using org.vpc.neormf.commons;
using org.vpc.neormf.commons.beans;
using org.vpc.neormf.commons.exceptions;
using org.vpc.neormf.commons.jwrapper;
using org.vpc.neormf.commons.util;
using System.Windows.Forms;
using org.vpc.neormf.util;

namespace org.vpc.neormf.wfs.util
{
	/// <summary>
	/// Description résumée de WFSUtils.
	/// </summary>
	public class WFSUtils
	{
		private WFSUtils()
		{
		}
		private static readonly ILog log =
			new ModuleLog("GUI", LogManager.GetLogger(MethodBase.GetCurrentMethod().DeclaringType));

		public static void ShowError(Exception e)
		{
			if (e is HandledException)
			{
				return;
			}
			try
			{
				if ((e.InnerException != null && e.InnerException is AbstractNeormfRuntimeException))
				{
					e = e.InnerException;
				}
				String msg = e.Message;
				MessageBoxIcon icon = MessageBoxIcon.Error;
				if (e is LocalizedObject)
				{
					((LocalizedObject) e).SetLocale(Locale.Current);
				}
				if (e is AbstractNeormfRuntimeException)
				{
					msg = ((AbstractNeormfRuntimeException) e).GetLocalizedMessage();
					//string str = ApplicationSession.Current.DefaultMessages.GetString(((AbstractNeormfRuntimeException) e).Message);
					icon = MessageBoxIcon.Exclamation;
				}
				log.Warn(msg, e);
				MessageBox.Show(msg == null ? "Erreur inconnue" : msg,
				                WFSApplication.Current.Messages.GetString("Default.Error", "Erreur"),
					MessageBoxButtons.OK, icon);
			}
			catch (Exception errorError)
			{
				log.Error(e.Message, e);
				log.Error(errorError.Message, errorError);
				MessageBox.Show(Convert.ToString(e == null ? "Erreur" : e.Message), "Erreur", MessageBoxButtons.OK,
					MessageBoxIcon.Error);
			}
		}

		public static String GetPreferredErrorMessage(Exception e)
		{
			String msg = e.Message;
			if (e is LocalizedObject)
			{
				((LocalizedObject) e).SetLocale(Locale.Current);
			}
			if (e is AbstractNeormfRuntimeException)
			{
				msg = ((AbstractNeormfRuntimeException) e).GetLocalizedMessage();
			}
			return msg;
		}

		public static void ShowError(String text)
		{
			MessageBox.Show(text, WFSApplication.Current.Messages.GetString("Default.Error"), MessageBoxButtons.OK,
				MessageBoxIcon.Error);
			//do extra log???
		}

		public static void ShowInfo(String text)
		{
			MessageBox.Show(text, WFSApplication.Current.Messages.GetString("Default.Info"), MessageBoxButtons.OK,
				MessageBoxIcon.Information);
		}

		public static void ShowInfoByCode(String code)
		{
			MessageBox.Show(WFSApplication.Current.Messages.GetString(code),
			                WFSApplication.Current.Messages.GetString("Default.Info"), MessageBoxButtons.OK,
				MessageBoxIcon.Information);
		}

		public static void ShowErrorByCode(String code)
		{
			MessageBox.Show(WFSApplication.Current.Messages.GetString(code),
			                WFSApplication.Current.Messages.GetString("Default.Error"), MessageBoxButtons.OK,
				MessageBoxIcon.Error);
			//do extra log???
		}

		public static void ShowSuccess(String text)
		{
			MessageBox.Show(text, WFSApplication.Current.Messages.GetString("Default.Success"), MessageBoxButtons.OK,
				MessageBoxIcon.Information);
			//do extra log???
		}

		public static void ShowSuccessByCode(String code)
		{
			MessageBox.Show(WFSApplication.Current.Messages.GetString(code),
			                WFSApplication.Current.Messages.GetString("Default.Success"), MessageBoxButtons.OK,
				MessageBoxIcon.Information);
			//do extra log???
		}

		public static bool ShowConfirm(String text)
		{
			return DialogResult.Yes == MessageBox.Show(
				text,
				WFSApplication.Current.Messages.GetString("Default.Warning"),
				MessageBoxButtons.YesNo, MessageBoxIcon.Warning, MessageBoxDefaultButton.Button2
				);
		}

		public static bool ShowConfirmByCode(String code)
		{
			return ShowConfirm(WFSApplication.Current.Messages.GetString(code));
		}

		public static bool ShowConfirmByCode(String code, Object[] parameters)
		{
			return ShowConfirm(WFSApplication.Current.Messages.GetString(code, parameters));
		}

		public static void SetSelected(ComboBox combo, DataKey key)
		{
			if (key != null)
			{
				for (int i = 0; i < combo.Items.Count; i++)
				{
					Object o = combo.Items[i];
					if (o is DataContent)
					{
						DataKey dk = ((DataContent) o).GetDataKey();
						if (dk != null && key.Equals(dk))
						{
							combo.SelectedIndex = i;
							return;
						}
					}
				}
			}
		}
		/*
		public static void FillDataGrid(DataGrid grid, PropertyList columns, DataContent[] rows)
		{
			grid.Invoke(FillDataGrid1);
		}
		
		public static void FillDataGrid1()
		{
		}
		*/
		
		private delegate void FillDataGridDelegate (DataGrid grid,PropertyList columns, DataContent[] rows);
		
		public static void FillDataGridThreadSafe(DataGrid grid, PropertyList columns, DataContent[] rows)
		{
			grid.Invoke(new FillDataGridDelegate(FillDataGrid),new object[]{grid,columns,rows});
		}
		
		public static void FillDataGrid(DataGrid grid, PropertyList columns, DataContent[] rows)
		{
			DataTable dtab = new DataTable();
			DataRow drow;
			int colNumber = 0;
			if (columns != null)
			{
				colNumber = columns.Size();
				dtab.Columns.Add("_UIUTilsNo_", typeof (int));
				for (IEnumerator i = columns.GetEnumerator(); i.MoveNext();)
				{
					DataInfo info = columns.Info();
					if (info != null)
					{
						DataField f = columns.Info().GetField((String) i.Current);
						dtab.Columns.Add(f.getFieldTitle(), typeof (Object) /*f.getFieldType().ToImplType()*/);
					}
					else
					{
						dtab.Columns.Add((String) i.Current, typeof (Object));
					}
				}

				for (int i = 0; i < rows.Length; i++)
				{
					drow = dtab.NewRow();
					int col = 0;
					drow[col++] = i + 1;
					for (IEnumerator e = columns.GetEnumerator(); e.MoveNext();)
					{
						Object o = rows[i].GetProperty((String) e.Current);
						if (o == null)
						{
							o = DBNull.Value;
						}
						drow[col++] = o;
					}
					dtab.Rows.Add(drow);
				}
			}
			else if (rows != null && rows.Length > 0)
			{
				ICollection cols = rows[0].KeySet();
				dtab.Columns.Add("No", typeof (int));

				for (IEnumerator i = cols.GetEnumerator(); i.MoveNext();)
				{
					dtab.Columns.Add((String) i.Current, typeof (Object));
					colNumber++;
				}

				for (int i = 0; i < rows.Length; i++)
				{
					drow = dtab.NewRow();
					int col = 0;
					drow[col++] = i + 1;
					for (IEnumerator e = cols.GetEnumerator(); e.MoveNext();)
					{
						Object o = rows[i].GetProperty((String) e.Current);
						if (o == null)
						{
							o = DBNull.Value;
						}
						drow[col++] = o;
					}
					dtab.Rows.Add(drow);
				}
			}
			int x=-1;
			if (colNumber > 0)
			{
				x = (grid.ClientSize.Width - 40)/colNumber;
				if (x > 0)
				{
					grid.PreferredColumnWidth = x;
				}
			}
			grid.DataSource = dtab;
			grid.TableStyles.Clear();
			grid.TableStyles.Add(new DataGridTableStyle());
			GridColumnStylesCollection gcsColl = grid.TableStyles[0].GridColumnStyles;
			ArrayList a=new ArrayList();
			for (int i = 0; i < gcsColl.Count; i++)
			{
				if (x > 0)
				{
					gcsColl[i].Width= x;
					gcsColl[i].NullText="";
				}
				a.Add(gcsColl[i]);
			}
			gcsColl.Clear();
			UserTextBoxColumn u=new UserTextBoxColumn();
			u.HeaderText="";
			u.MappingName="_UIUTilsNo_";
			u.Width=30;
			gcsColl.Add(u);
			for (int i = 1; i < a.Count; i++)
			{
				gcsColl.Add((DataGridColumnStyle) a[i]);
			}
			/*
			for (int i = 0; i < gcsColl.Count; i++)
			{
				if (gcsColl[i].GetType() == typeof (DataGridTextBoxColumn))
				{
					DataGridTextBoxColumn textColumn = (DataGridTextBoxColumn) gcsColl[i];
					if (textColumn.HeaderText == "No")
					{
						textColumn.Width = 30;
						textColumn.TextBox.BackColor=Color.Red;
						//textColumn.HeaderText = "new header text";
						break;
					}
				}
			}*/

			if (dtab.Rows.Count > 0)
			{
				grid.CurrentRowIndex = 0;
				grid.Select(0);
			}
			grid.RowHeadersVisible=false;
			grid.RowHeaderWidth=1;
			//grid.Focus();
		}

		public static int ValidateIntFromString(TextBox input, int oldValue, String fieldName, int min, int max,
			bool acceptNull, int valueIfNull)
		{
			return ValidateIntFromString(input, oldValue.ToString(), fieldName, min, max, acceptNull, valueIfNull);
		}

		/// <summary>
		/// Validates the Textbox Content as Integer, if error occurs restaures <code>oldValue</code>
		/// </summary>
		/// <param name="acceptNull">Value is nullable</param>
		/// <param name="valueIfNull">Value if the entred value is nullable</param>
		/// <param name="fieldName">Field name used ti generate appropriate exception message</param>
		/// <param name="input">Textbox to parse</param>
		/// <param name="max">Maximum value allowed</param>
		/// <param name="min">Minimum value allowed</param>
		/// <param name="oldValue">Old value restaured if any error</param>
		/// <exception cref="HandledException"> if error occurs, appropriate error message will be poped up in MessageBox</exception>
		public static int ValidateIntFromString(TextBox input, String oldValue, String fieldName, int min, int max,
			bool acceptNull, int valueIfNull)
		{
			try
			{
				return ValidateIntFromString(input.Text, fieldName, min, max, acceptNull, valueIfNull);
			}
			catch (Exception exc)
			{
				MessageBox.Show(exc.Message, "Erreur", MessageBoxButtons.OK, MessageBoxIcon.Hand, MessageBoxDefaultButton.Button1);
				input.Text = oldValue;
				input.Focus();
				throw new HandledException();
			}
		}

		/// <summary>
		/// Validates the Textbox Content as String, if error occurs restaures <code>oldValue</code>
		/// </summary>
		/// <param name="acceptNull">Value is nullable</param>
		/// <param name="valueIfNull">Value if the entred value is nullable</param>
		/// <param name="fieldName">Field name used ti generate appropriate exception message</param>
		/// <param name="input">Textbox to parse</param>
		/// <param name="maxLen">Maximum String Length allowed</param>
		/// <param name="minLen">Minimum String Length allowed</param>
		/// <param name="oldValue">Old value restaured if any error</param>
		/// <exception cref="HandledException"> if error occurs, appropriate error message will be poped up in MessageBox</exception>
		public static String ValidateStringFromString(TextBox input, String oldValue, String fieldName, int minLen, int maxLen,
			bool acceptNull, String valueIfNull)
		{
			try
			{
				return ValidateStringFromString(input.Text, fieldName, minLen, maxLen, acceptNull, valueIfNull);
			}
			catch (Exception exc)
			{
				MessageBox.Show(exc.Message, "Erreur", MessageBoxButtons.OK, MessageBoxIcon.Hand, MessageBoxDefaultButton.Button1);
				input.Text = oldValue;
				input.Focus();
				throw new HandledException();
			}
		}

		/// <summary>
		/// Validates the String as Integer</code>
		/// </summary>
		/// <param name="acceptNull">Value is nullable</param>
		/// <param name="valueIfNull">Value if the entred value is nullable</param>
		/// <param name="fieldName">Field name used ti generate appropriate exception message</param>
		/// <param name="input">Textbox to parse</param>
		/// <param name="max">Maximum value allowed</param>
		/// <param name="min">Minimum value allowed</param>
		public static int ValidateIntFromString(String input, String fieldName, int min, int max, bool acceptNull,
			int valueIfNull)
		{
			if (input == null || input.Equals(""))
			{
				if (acceptNull)
				{
					return valueIfNull;
				}
				else
				{
					throw new BadDataException("Le champ " + fieldName + " doit être spécifié");
				}
			}
			int x = 0;
			try
			{
				x = Convert.ToInt32(input);
			}
			catch (Exception)
			{
				throw new BadDataException("Le champ " + fieldName + " doit être numérique");
			}
			if (min != int.MinValue && min != int.MaxValue)
			{
				if (x < min)
				{
					throw new BadDataException("La valeur du champ " + fieldName + " est très faible");
				}
			}
			if (max != int.MinValue && max != int.MaxValue)
			{
				if (x > max)
				{
					throw new BadDataException("La valeur du champ " + fieldName + " est trop élevée");
				}
			}
			return x;
		}

		/// <summary>
		/// Validates the String</code>
		/// </summary>
		/// <param name="acceptNull">Value is nullable</param>
		/// <param name="valueIfNull">Value if the entred value is nullable</param>
		/// <param name="fieldName">Field name used ti generate appropriate exception message</param>
		/// <param name="input">Textbox to parse</param>
		/// <param name="maxLen">Maximum String Length allowed</param>
		/// <param name="minLen">Minimum String Length allowed</param>
		public static String ValidateStringFromString(String input, String fieldName, int minLen, int maxLen, bool acceptNull,
			String valueIfNull)
		{
			if (input == null || input.Equals(""))
			{
				if (acceptNull)
				{
					return valueIfNull;
				}
				else
				{
					throw new BadDataException("Le champ " + fieldName + " doit être spécifié");
				}
			}
			int x = input.Length;
			if (minLen != int.MinValue && minLen != int.MaxValue)
			{
				if (x < minLen)
				{
					throw new BadDataException("La valeur du champ " + fieldName + " est trop courte");
				}
			}
			if (maxLen != int.MinValue && maxLen != int.MaxValue)
			{
				if (x > maxLen)
				{
					throw new BadDataException("La valeur du champ " + fieldName + " est trop longue");
				}
			}
			return input;
		}
		
		public static int GetCurrentRowIndex(DataGrid dg)
		{
			DataRow r=GetCurrentRow(dg);
			if(r==null)
			{
				return -1;
			}
			return ((int)r[0])-1;
		}
		
		public static DataRow GetCurrentRow(DataGrid dg)
		{
			CurrencyManager cm =(CurrencyManager)dg.BindingContext[dg.DataSource,dg.DataMember];
			DataView theView = (DataView)cm.List;
			int ci=dg.CurrentRowIndex;
			if(ci<0)
			{
				return null;
			}
			return theView[ci].Row;			
		}

		public static DataContent[] AminusB(DataContent[] a,DataContent[] b)
		{
			return AminusB(a,b,typeof(DataContent));
		}

		public static DataContent[] AminusB(DataContent[] a,DataContent[] b,Type itemType)
		{
			ArrayList ret=new ArrayList();
			for (int i = 0; i < a.Length; i++)
			{
				DataKey ai = a[i].GetDataKey();
				bool found=false;
				for (int j = 0; j < b.Length; j++)
				{
					DataKey bi = b[j].GetDataKey();
					if(ai.Equals(bi))
					{
						found=true;
					}
				}
				if(!found)
				{
					ret.Add(a[i]);
				}
			}
			return (DataContent[]) ret.ToArray(itemType);
		}

		public static int IndexOf(DataKey k, DataContent[] tab)
		{
			for (int i = 0; i < tab.Length; i++)
			{
				if (tab[i].GetDataKey().Equals(k))
				{
					return i;
				}
			}
			return -1;
		}

		public static int IndexOf(DataKey k, ArrayList tab)
		{
			for (int i = 0; i < tab.Count; i++)
			{
				if (((DataContent) tab[i]).GetDataKey().Equals(k))
				{
					return i;
				}
			}
			return -1;
		}

		public static TokenDesc[] TokenizeLine(String line, TokenDesc[] desc)
		{
			String[]  parts=line.Split(new char[]{';'});
			int max = desc.Length;
			//int max = Math.Max(desc.Length,parts.Length);
			for (int i = 0; i < max; i++)
			{
				String s=parts[i].Trim();
				if(s.StartsWith("\"") && s.EndsWith("\""))
				{
					s=s.Substring(1,s.Length-2);
				}
				if(s.Length>desc[i].Width)
				{
					s=s.Substring(0,desc[i].Width);
				}
				desc[i].StringValue = s;
			}
			return desc;
		}

		public static String FormatNameUsingDate(String name, DateTime now, String defaultDateFormat)
		{
			int i0 = name.IndexOf('{');
			if (i0 >= 0)
			{
				int i1 = name.IndexOf('}', i0 + 1);
				if (i1 >= 0)
				{
					String pattern = name.Substring(i0 + 1, i1 - i0 - 1);
					if (pattern.StartsWith("@"))
					{
						if (pattern.Equals("@"))
						{
							pattern = now.ToString(defaultDateFormat == null ? "yyyyMMddHHmmss" : defaultDateFormat);
						}
						else
						{
							pattern = now.ToString(pattern.Substring(1));
						}
					}
					else if (pattern.Equals("user"))
					{
						pattern = WFSApplication.Current.CallerPrincipalName;
					}
					else
					{
						pattern = now.ToString(pattern);
					}
					name = name.Substring(0, i0) + pattern + ((i1 + 1 < name.Length) ? name.Substring(i1 + 1) : "");
				}
				return FormatNameUsingDate(name, now, null);
			}
			else if (defaultDateFormat != null)
			{
				int i1 = name.LastIndexOf('.');
				if (i1 > 0)
				{
					name = name.Substring(0, i1) + "{@" + defaultDateFormat + "}" + name.Substring(i1, name.Length - i1);
				}
				else
				{
					name = name + "{@" + defaultDateFormat + "}";
				}
				return FormatNameUsingDate(name, now, null);
			}
			return name;
		}
		
		public static double ParseDouble(String str)
		{
			System.Globalization.CultureInfo ci = System.Globalization.CultureInfo.InstalledUICulture;
			System.Globalization.NumberFormatInfo ni = (System.Globalization.NumberFormatInfo) ci.NumberFormat.Clone();
			try
			{
				return Double.Parse(str,ni);
			}
			catch(FormatException)
			{
				ni.NumberDecimalSeparator=".";
				return Double.Parse(str,ni);
			}
		}


	}
}
