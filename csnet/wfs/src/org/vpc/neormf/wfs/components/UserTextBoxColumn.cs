using System;
using System.Drawing;
using System.Windows.Forms;

namespace org.vpc.neormf.wfs.util
{
	/// <summary>
	/// Description résumée de UserTextBoxColumn.
	/// </summary>
	public class UserTextBoxColumn: DataGridTextBoxColumn
	{
		private Font fontVal;
		private Brush backBrushVal;
		private Brush foreBrushVal;

		public UserTextBoxColumn()
		{
			backBrushVal=new SolidBrush(SystemColors.Control);
			foreBrushVal=new SolidBrush(SystemColors.GrayText);
			fontVal=new Label().Font;
		}
		
		protected override void Paint(Graphics g, Rectangle bounds, CurrencyManager source, int rowNum, Brush backBrush, Brush foreBrush, bool alignToRight)
		{
			if(backBrushVal!=null)
			{
				backBrush=backBrushVal;
			}
			g.FillRectangle(backBrush, bounds);
			Region saveRegion = g.Clip;
			Rectangle rect = new Rectangle(bounds.X, bounds.Y, bounds.Width, bounds.Height);
			using(Region newRegion = new Region(rect))
			{
				g.Clip = newRegion;
				int charWidth = (int) Math.Ceiling(g.MeasureString("c", TextFont, 20, StringFormat.GenericTypographic).Width);

				string s = this.GetColumnValueAtRow(source, rowNum).ToString();
				int maxChars = Math.Min(s.Length,  (bounds.Width / charWidth));

				try
				{
					g.DrawString(s.Substring(0, maxChars), TextFont, ForeBrush, bounds.X, bounds.Y + 2);
				}
				catch(Exception ex)
				{
					Console.WriteLine(ex.Message.ToString());
				} //empty catch
				finally
				{
					g.Clip = saveRegion;
				}
			}
		}
		
		//font used for drawing the text
		public Font TextFont
		{
			get{ return fontVal;}
			set{ fontVal = value;}
		}
		//foreground brush
		public Brush ForeBrush
		{
			get{ return foreBrushVal;}
			set{ foreBrushVal = value;}
		}
		public Brush BackBrush
		{
			get{ return backBrushVal;}
			set{ backBrushVal = value;}
		}
		
	}
		
	
}
