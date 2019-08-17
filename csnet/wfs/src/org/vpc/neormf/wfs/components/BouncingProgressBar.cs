using System;
using System.Collections;
using System.ComponentModel;
using System.Drawing;
using System.Data;
using System.Threading;
using System.Windows.Forms;
using System.Drawing.Drawing2D;

namespace org.vpc.neormf.wfs.util
{
	/// <summary>
	/// Summary description for UserControl1.
	/// </summary>
	public class BouncingProgressBar : System.Windows.Forms.UserControl
	{
		private System.ComponentModel.IContainer components;

		public enum LocTecBarStyle
		{
			SmoothBounce,
			BlockBounce
		}

		public delegate void Stopped(object o, BouncingProgressBarEvent e);
		public event Stopped EventStopped;

		public class BouncingProgressBarEvent: EventArgs
		{
			private Exception error;
			public BouncingProgressBarEvent(Exception error)
			{
				this.error=error;
			}

			public Exception Error
			{
				get { return error; }
			}
		}

		public BouncingProgressBar()
		{
			// This call is required by the Windows.Forms Form Designer.
			InitializeComponent();

			// TODO: Add any initialization after the InitComponent call

		}

		/// <summary>
		/// Clean up any resources being used.
		/// </summary>
		protected override void Dispose( bool disposing )
		{
			if( disposing )
			{
				if( components != null )
					components.Dispose();
			}
			base.Dispose( disposing );
		}

		#region Component Designer generated code
		/// <summary>
		/// Required method for Designer support - do not modify 
		/// the contents of this method with the code editor.
		/// </summary>
		private void InitializeComponent()
		{
			this.components = new System.ComponentModel.Container();
			this.bouncingSpeedTimer = new System.Windows.Forms.Timer(this.components);
			// 
			// bouncingSpeedTimer
			// 
			this.bouncingSpeedTimer.Tick += new System.EventHandler(this.bouncingSpeedTimer_Tick);
			// 
			// BouncingProgressBar
			// 
			this.Name = "BouncingProgressBar";
			this.Size = new System.Drawing.Size(192, 16);

		}
		#endregion

		private LocTecBarStyle style = LocTecBarStyle.BlockBounce;
		private int min = 0;	// Minimum value for progress range
		private int max = 100;	// Maximum value for progress range
		private int val = 0;		// Current progress
		private int blockSpace = 3;	// Width of the space between blocks
		private int blockWidth = 12;	// Width of the blocks
		private bool increasing = true;	// Does the progress bar start on the upswing, yes
		private int bouncingSpeed = 1;	// Timer.Interval in milliseconds
		private System.Windows.Forms.Timer bouncingSpeedTimer;
		Color BarColor = Color.DarkBlue;		// Color of progress meter

		protected override void OnResize(EventArgs e)
		{
			max = this.ClientRectangle.Width;
			// Invalidate the control to get a repaint.
			this.Invalidate();
		}

		protected override void OnPaint(PaintEventArgs e)
		{
			Graphics g = e.Graphics;
			SolidBrush brush = new SolidBrush(BarColor);
			SolidBrush spaceBrush = new SolidBrush(this.BackColor);
			float percent = (float)(val - min) / (float)(max - min);

			switch (style)
			{
				case (LocTecBarStyle.SmoothBounce):
				{
					Rectangle rect = this.ClientRectangle;

					// Set the area for drawing the progress.
					rect.Width = blockWidth;

					// Set the new location of the block
					rect.X = val;

					// Draw the progress meter.
					g.FillRectangle(brush, rect);
					break;
				}
				case (LocTecBarStyle.BlockBounce):
				{
					Rectangle gradientTop = this.ClientRectangle;
					Rectangle gradientBottom = this.ClientRectangle;
					Rectangle space = this.ClientRectangle;

					// Set the Width for drawing the progress.
					gradientTop.Width = (blockWidth * 2) + blockSpace;
					gradientBottom.Width = (blockWidth * 2) + blockSpace;

					space.Width = blockSpace;

					// Height
					gradientTop.Height = (int)(ClientRectangle.Height * .5) - 1;
					gradientBottom.Height = ClientRectangle.Height - gradientTop.Height; //(int)(ClientRectangle.Height * .5);

					// Top
					gradientTop.Y = ClientRectangle.Y;
					gradientBottom.Y = gradientTop.Bottom;

					// Left
					gradientTop.X = val;
					gradientBottom.X = val;
					
					space.X = val + blockWidth;

					// Gradient
					LinearGradientMode lgmTop = LinearGradientMode.Vertical;
					using (LinearGradientBrush lgbTop = new LinearGradientBrush(gradientTop, this.BackColor, BarColor, lgmTop))
						g.FillRectangle(lgbTop, gradientTop);

					LinearGradientMode lgmBottom = LinearGradientMode.Vertical;
					using (LinearGradientBrush lgbBottom = new LinearGradientBrush(gradientBottom, BarColor, this.BackColor, lgmBottom))
						g.FillRectangle(lgbBottom, gradientBottom);

					// Draw the progress meter.
					g.FillRectangle(spaceBrush, space);
					break;
				}
			}
			// Draw a three-dimensional border around the control.
			Draw3DBorder(g);

			// Clean up.
			brush.Dispose();
			g.Dispose();
		}

		public LocTecBarStyle Style
		{
			get {return style;}
			set {style = value;}
		}

		public int BlockWidth
		{
			get
			{
				return blockWidth;
			}

			set
			{
				blockWidth = value;
				// Prevent a negative value.
				if (value < 0)
				{
					blockWidth = 1;
				}

				// Make sure that the blockWidth is never set higher than the maximum value.
				if (value > max)
				{
					blockWidth = max;
				}

				// Invalidate the control to get a repaint.
				this.Invalidate();
			}
		}

		public int BouncingSpeed
		{
			get
			{
				return bouncingSpeed;
			}

			set
			{
				if (value < 1)
				{
					bouncingSpeed = 1;
				}
				else
				{
					bouncingSpeed = value;
				}
				bouncingSpeedTimer.Interval = bouncingSpeed;
			}
		}

		public int Value
		{
			get
			{
				return val;
			}

		}

		public void ResetValue()
		{
			val=0;
			this.Invalidate(this.ClientRectangle);
		}

		private void UpdateValue()
		{
			if (increasing)
			{
				val++;
			}
			else
			{
				val--;
			}
			//int oldValue = val;

			// Make sure that the value does not stray outside the valid range.
			if (val < min)
			{
				val = min;
				increasing = true;
			}
			else if ((style == LocTecBarStyle.SmoothBounce) && (val > (max - blockWidth)))
			{
				val = (max - blockWidth);
				increasing = false;
			}
			else if ((style == LocTecBarStyle.BlockBounce) && (val > (max - (blockWidth * 2) - blockSpace)))
			{
				val = (max - (blockWidth * 2) - blockSpace);
				increasing = false;
			}

			Rectangle invalidateLeftArea = this.ClientRectangle;
			Rectangle invalidateRightArea = this.ClientRectangle;
			Rectangle invalidateInnerLeftArea = this.ClientRectangle;
			Rectangle invalidateInnerRightArea = this.ClientRectangle;

			invalidateLeftArea.Width = 1;
			invalidateRightArea.Width = 1;
			invalidateInnerLeftArea.Width = 1;
			invalidateInnerRightArea.Width = 1;

			if (increasing)
			{
				invalidateLeftArea.X = (val - 1);
				invalidateRightArea.X = (val + blockWidth - 1);
				if (style == LocTecBarStyle.BlockBounce)
				{
					invalidateRightArea.X = (val + (blockWidth * 2) + blockSpace - 1);
					invalidateInnerLeftArea.X = (val + blockWidth - 1);
					invalidateInnerRightArea.X = (val + blockWidth + blockSpace - 1);
				}
			}
			else
			{
				invalidateRightArea.X = (val + blockWidth);
				invalidateLeftArea.X = (val);
				if (style == LocTecBarStyle.BlockBounce)
				{
					invalidateRightArea.X = (val + (blockWidth * 2) + blockSpace);
					invalidateInnerRightArea.X = (val + blockWidth + blockSpace);
					invalidateInnerLeftArea.X = (val + blockWidth);
				}
			}

			// Invalidate the intersection region only.
			this.Invalidate(invalidateLeftArea);
			this.Invalidate(invalidateRightArea);
			if (style == LocTecBarStyle.BlockBounce)
			{
				this.Invalidate(invalidateInnerLeftArea);
				this.Invalidate(invalidateInnerRightArea);
			}
		}

		public Color ProgressBarColor
		{
			get
			{
				return BarColor;
			}

			set
			{
				BarColor = value;

				// Invalidate the control to get a repaint.
				this.Invalidate();
			}
		}

		private void Draw3DBorder(Graphics g)
		{
			int PenWidth = (int)Pens.White.Width;

			g.DrawLine(Pens.DarkGray,
				new Point(this.ClientRectangle.Left, this.ClientRectangle.Top),
				new Point(this.ClientRectangle.Width - PenWidth, this.ClientRectangle.Top));
			g.DrawLine(Pens.DarkGray,
				new Point(this.ClientRectangle.Left, this.ClientRectangle.Top),
				new Point(this.ClientRectangle.Left, this.ClientRectangle.Height - PenWidth));
			g.DrawLine(Pens.White,
				new Point(this.ClientRectangle.Left, this.ClientRectangle.Height - PenWidth),
				new Point(this.ClientRectangle.Width - PenWidth, this.ClientRectangle.Height - PenWidth));
			g.DrawLine(Pens.White,
				new Point(this.ClientRectangle.Width - PenWidth, this.ClientRectangle.Top),
				new Point(this.ClientRectangle.Width - PenWidth, this.ClientRectangle.Height - PenWidth));
		}

		public void Start()
		{
			bouncingSpeedTimer.Enabled = true;
		}

		private IProgressTask task;

		public void Start(IProgressTask task)
		{
			this.task=task;
			Thread t = new Thread(new ThreadStart(RunningMethodDelegate));
			Start();
			t.Start();
		}

		public void RunningMethodDelegate()
		{
			BouncingProgressBarEvent evt=null;
			try
			{
				task.RunTask();
				evt=new BouncingProgressBarEvent(null);
			}
			catch (Exception e)
			{
				evt=new BouncingProgressBarEvent(e);
			}
			Stop();
			if(EventStopped!=null)
			{
				EventStopped(new object(),evt);
			}
		}

		public void Stop()
		{
			bouncingSpeedTimer.Enabled = false;
		}

		private void bouncingSpeedTimer_Tick(object sender, System.EventArgs e)
		{
			UpdateValue();
		}

		public bool Started
		{
			get {return bouncingSpeedTimer.Enabled;}
		}
	}
}
