using System;
using System.ComponentModel;
using System.Drawing;
using System.Threading;
using System.Windows.Forms;

namespace org.vpc.neormf.wfs.util
{
	/// <summary>
	/// Description résumée de ProgressDialog.
	/// </summary>
	public class ProgressDialog : Form
	{
		private ProgressBar progressBar1;

		/// <summary>
		/// Variable nécessaire au concepteur.
		/// </summary>
		private Container components = null;

		private Label label;

		public delegate void RunTask(object o, AppTaskEvent e);
		public delegate void OnTaskSuccess(object o, AppTaskEvent e);
		public delegate void OnTaskError(object o, AppTaskEvent e);
		public event RunTask EventRunTask;
		public event OnTaskSuccess EventOnTaskSuccess;
		public event OnTaskError EventOnTaskError;
		
		private bool finished=true;
		public class AppTaskEvent: EventArgs
		{
			private Exception error;
			public AppTaskEvent(Exception error)
			{
				this.error=error;
			}

			public Exception Error
			{
				get { return error; }
			}
		}

		public int SleepTime
		{
			get { return sleepTime; }
			set { sleepTime = value; }
		}

		private int sleepTime;


		public ProgressDialog()
		{
			//
			// Requis pour la prise en charge du Concepteur Windows Forms
			//
			InitializeComponent();

			//
			// TODO : ajoutez le code du constructeur après l'appel à InitializeComponent
			//
		}

		/// <summary>
		/// Nettoyage des ressources utilisées.
		/// </summary>
		protected override void Dispose(bool disposing)
		{
			if (disposing)
			{
				if (components != null)
				{
					components.Dispose();
				}
			}
			base.Dispose(disposing);
		}

		#region Code généré par le Concepteur Windows Form

		/// <summary>
		/// Méthode requise pour la prise en charge du concepteur - ne modifiez pas
		/// le contenu de cette méthode avec l'éditeur de code.
		/// </summary>
		private void InitializeComponent()
		{
			this.progressBar1 = new System.Windows.Forms.ProgressBar();
			this.label = new System.Windows.Forms.Label();
			this.SuspendLayout();
			// 
			// progressBar1
			// 
			this.progressBar1.Location = new System.Drawing.Point(8, 8);
			this.progressBar1.Name = "progressBar1";
			this.progressBar1.Size = new System.Drawing.Size(360, 16);
			this.progressBar1.TabIndex = 0;
			// 
			// label
			// 
			this.label.Location = new System.Drawing.Point(376, 8);
			this.label.Name = "label";
			this.label.Size = new System.Drawing.Size(80, 16);
			this.label.TabIndex = 1;
			// 
			// ProgressDialog
			// 
			this.AutoScaleBaseSize = new System.Drawing.Size(5, 13);
			this.ClientSize = new System.Drawing.Size(464, 29);
			this.ControlBox = false;
			this.Controls.Add(this.label);
			this.Controls.Add(this.progressBar1);
			this.Cursor = System.Windows.Forms.Cursors.AppStarting;
			this.MaximizeBox = false;
			this.MinimizeBox = false;
			this.Name = "ProgressDialog";
			this.ShowInTaskbar = false;
			this.StartPosition = System.Windows.Forms.FormStartPosition.CenterParent;
			this.Text = "Progression en cours...";
			this.TopMost = true;
			this.ResumeLayout(false);

		}

		#endregion

		public void RunProgress(Form win)
		{
			Thread t = new Thread(new ThreadStart(RunningMethodDelegate));
			t.Start();
			Thread t2 = new Thread(new ThreadStart(AnimateProgressBar));
			t2.Start();
			if (win == null || !win.Visible)
			{
				this.StartPosition = FormStartPosition.CenterScreen;
				ShowDialog();
			}
			else
			{
				this.StartPosition = FormStartPosition.CenterParent;
				ShowDialog(win);
			}
		}

		public void RunningMethodDelegate()
		{
			try
			{
				finished=false;
				AppTaskEvent evt=new AppTaskEvent(null);
				if(EventRunTask!=null)
				{
					EventRunTask(new object(),evt);
				}
				if(EventOnTaskSuccess!=null)
				{
					EventOnTaskSuccess(new object(),evt);
				}
			}
			catch (Exception e)
			{
				AppTaskEvent evt=new AppTaskEvent(e);
				Console.WriteLine(e.StackTrace);
				if(EventOnTaskError!=null)
				{
					EventOnTaskError(new object(),evt);
				}
			}
			finally
			{
				finished=true;
			}
		}

		public void AnimateProgressBar()
		{
			DateTime start = DateTime.Now;
			bool asc = true;
			while (!finished)
			{
				Thread.Sleep(sleepTime);
				if (asc)
				{
					if (progressBar1.Value < 100)
					{
						progressBar1.Value++;
					}
					else
					{
						asc = false;
					}
				}
				else
				{
					if (progressBar1.Value > 0)
					{
						progressBar1.Value--;
					}
					else
					{
						asc = true;
					}
				}
				int s = (int) ((DateTime.Now - start).TotalSeconds);
				long m = s/60;
				s = s%60;
				label.Text = "" + m + " min " + s + " secs";
			}
			Hide();
		}

	}
}