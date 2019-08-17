using System;
using System.Collections;
using System.ComponentModel;
using System.Drawing;
using System.Windows.Forms;
using org.vpc.neormf.commons.beans;

namespace org.vpc.neormf.wfs.util
{
	/// <summary>
	/// Description résumée de ListSelector.
	/// </summary>
	public class ListSelector : UserControl
	{
		private DataGrid rightGrid;
		private DataGrid leftGrid;
		private Button moveAllLeftButton;
		private Button moveAllRightButton;
		private Button moveRightButton;
		private Button moveLeftButton;
		private Label rightTitleLabel;
		private Label leftTitleLabel;

		/// <summary> 
		/// Variable nécessaire au concepteur.
		/// </summary>
		private Container components = null;

		public DataContent[] LeftItems
		{
			get { return leftItems; }
		}

		private DataContent[] leftItems;

		private DataContent[] rightItems;
		private PropertyList leftPropertyList;
		private PropertyList rightPropertyList;
		private EventHandler moveAllLeftButtonEventHandler;
		private EventHandler moveAllRightButtonEventHandler;
		private EventHandler moveLeftButtonEventHandler;
		private EventHandler moveRightButtonEventHandler;
		private DataContent[] initialLeft;
		private DataContent[] initialRight;
		private Type itemType=typeof(DataContent);
		public ListSelector()
		{
			// Cet appel est requis par le Concepteur de formulaires Windows.Forms.
			InitializeComponent();
			LeftData = new DataContent[0];
			RightData = new DataContent[0];
			WFSUtils.FillDataGrid(leftGrid, LeftPropertyList, leftItems);
			WFSUtils.FillDataGrid(rightGrid, RightPropertyList, rightItems);
			moveAllLeftButtonEventHandler= new System.EventHandler(this.moveAllLeftButton_Click);
			moveAllLeftButton.Click+=moveAllLeftButtonEventHandler;
			moveAllRightButtonEventHandler= new System.EventHandler(this.moveAllRightButton_Click);
			moveAllRightButton.Click+=moveAllRightButtonEventHandler;
			moveLeftButtonEventHandler= new System.EventHandler(this.moveLeftButton_Click);
			moveLeftButton.Click+=moveLeftButtonEventHandler;
			moveRightButtonEventHandler= new System.EventHandler(this.moveRightButton_Click);
			moveRightButton.Click+=moveRightButtonEventHandler;
			// TODO : ajoutez les initialisations après l'appel à InitializeComponent

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

		#region Code généré par le Concepteur de composants

		/// <summary> 
		/// Méthode requise pour la prise en charge du concepteur - ne modifiez pas 
		/// le contenu de cette méthode avec l'éditeur de code.
		/// </summary>
		private void InitializeComponent()
		{
			System.Resources.ResourceManager resources = new System.Resources.ResourceManager(typeof(ListSelector));
			this.rightGrid = new System.Windows.Forms.DataGrid();
			this.leftGrid = new System.Windows.Forms.DataGrid();
			this.moveAllLeftButton = new System.Windows.Forms.Button();
			this.moveAllRightButton = new System.Windows.Forms.Button();
			this.moveRightButton = new System.Windows.Forms.Button();
			this.moveLeftButton = new System.Windows.Forms.Button();
			this.rightTitleLabel = new System.Windows.Forms.Label();
			this.leftTitleLabel = new System.Windows.Forms.Label();
			((System.ComponentModel.ISupportInitialize)(this.rightGrid)).BeginInit();
			((System.ComponentModel.ISupportInitialize)(this.leftGrid)).BeginInit();
			this.SuspendLayout();
			// 
			// rightGrid
			// 
			this.rightGrid.AllowNavigation = false;
			this.rightGrid.AllowSorting = false;
			this.rightGrid.BackgroundColor = System.Drawing.Color.Gainsboro;
			this.rightGrid.CaptionBackColor = System.Drawing.SystemColors.Desktop;
			this.rightGrid.DataMember = "";
			this.rightGrid.GridLineColor = System.Drawing.Color.Gainsboro;
			this.rightGrid.HeaderBackColor = System.Drawing.Color.Gainsboro;
			this.rightGrid.HeaderForeColor = System.Drawing.SystemColors.ControlText;
			this.rightGrid.LinkColor = System.Drawing.SystemColors.Desktop;
			this.rightGrid.Location = new System.Drawing.Point(408, 24);
			this.rightGrid.Name = "rightGrid";
			this.rightGrid.ParentRowsBackColor = System.Drawing.Color.Gainsboro;
			this.rightGrid.ReadOnly = true;
			this.rightGrid.SelectionBackColor = System.Drawing.SystemColors.Desktop;
			this.rightGrid.Size = new System.Drawing.Size(286, 136);
			this.rightGrid.TabIndex = 80;
			this.rightGrid.MouseUp += new System.Windows.Forms.MouseEventHandler(this.Grid_MouseUp);
			// 
			// leftGrid
			// 
			this.leftGrid.AllowNavigation = false;
			this.leftGrid.AllowSorting = false;
			this.leftGrid.BackgroundColor = System.Drawing.Color.Gainsboro;
			this.leftGrid.CaptionBackColor = System.Drawing.SystemColors.Desktop;
			this.leftGrid.DataMember = "";
			this.leftGrid.GridLineColor = System.Drawing.Color.Gainsboro;
			this.leftGrid.HeaderBackColor = System.Drawing.Color.Gainsboro;
			this.leftGrid.HeaderForeColor = System.Drawing.SystemColors.ControlText;
			this.leftGrid.LinkColor = System.Drawing.SystemColors.Desktop;
			this.leftGrid.Location = new System.Drawing.Point(4, 24);
			this.leftGrid.Name = "leftGrid";
			this.leftGrid.ParentRowsBackColor = System.Drawing.Color.Gainsboro;
			this.leftGrid.ReadOnly = true;
			this.leftGrid.SelectionBackColor = System.Drawing.SystemColors.Desktop;
			this.leftGrid.Size = new System.Drawing.Size(286, 136);
			this.leftGrid.TabIndex = 75;
			this.leftGrid.MouseUp += new System.Windows.Forms.MouseEventHandler(this.Grid_MouseUp);
			// 
			// moveAllLeftButton
			// 
			this.moveAllLeftButton.Image = ((System.Drawing.Image)(resources.GetObject("moveAllLeftButton.Image")));
			this.moveAllLeftButton.ImageAlign = System.Drawing.ContentAlignment.MiddleLeft;
			this.moveAllLeftButton.Location = new System.Drawing.Point(352, 56);
			this.moveAllLeftButton.Name = "moveAllLeftButton";
			this.moveAllLeftButton.Size = new System.Drawing.Size(48, 32);
			this.moveAllLeftButton.TabIndex = 77;
			// 
			// moveAllRightButton
			// 
			this.moveAllRightButton.Image = ((System.Drawing.Image)(resources.GetObject("moveAllRightButton.Image")));
			this.moveAllRightButton.ImageAlign = System.Drawing.ContentAlignment.MiddleLeft;
			this.moveAllRightButton.Location = new System.Drawing.Point(300, 96);
			this.moveAllRightButton.Name = "moveAllRightButton";
			this.moveAllRightButton.Size = new System.Drawing.Size(48, 32);
			this.moveAllRightButton.TabIndex = 78;
			// 
			// moveRightButton
			// 
			this.moveRightButton.Image = ((System.Drawing.Image)(resources.GetObject("moveRightButton.Image")));
			this.moveRightButton.ImageAlign = System.Drawing.ContentAlignment.MiddleLeft;
			this.moveRightButton.Location = new System.Drawing.Point(352, 96);
			this.moveRightButton.Name = "moveRightButton";
			this.moveRightButton.Size = new System.Drawing.Size(48, 32);
			this.moveRightButton.TabIndex = 79;
			// 
			// moveLeftButton
			// 
			this.moveLeftButton.Image = ((System.Drawing.Image)(resources.GetObject("moveLeftButton.Image")));
			this.moveLeftButton.ImageAlign = System.Drawing.ContentAlignment.MiddleLeft;
			this.moveLeftButton.Location = new System.Drawing.Point(300, 56);
			this.moveLeftButton.Name = "moveLeftButton";
			this.moveLeftButton.Size = new System.Drawing.Size(48, 32);
			this.moveLeftButton.TabIndex = 76;
			// 
			// rightTitleLabel
			// 
			this.rightTitleLabel.Font = new System.Drawing.Font("Verdana", 8.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((System.Byte)(0)));
			this.rightTitleLabel.Location = new System.Drawing.Point(356, 0);
			this.rightTitleLabel.Name = "rightTitleLabel";
			this.rightTitleLabel.Size = new System.Drawing.Size(284, 24);
			this.rightTitleLabel.TabIndex = 82;
			this.rightTitleLabel.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
			// 
			// leftTitleLabel
			// 
			this.leftTitleLabel.Font = new System.Drawing.Font("Verdana", 8.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((System.Byte)(0)));
			this.leftTitleLabel.Location = new System.Drawing.Point(8, 0);
			this.leftTitleLabel.Name = "leftTitleLabel";
			this.leftTitleLabel.Size = new System.Drawing.Size(280, 24);
			this.leftTitleLabel.TabIndex = 81;
			this.leftTitleLabel.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
			// 
			// ListSelector
			// 
			this.Controls.Add(this.rightGrid);
			this.Controls.Add(this.leftGrid);
			this.Controls.Add(this.moveAllLeftButton);
			this.Controls.Add(this.moveAllRightButton);
			this.Controls.Add(this.moveRightButton);
			this.Controls.Add(this.moveLeftButton);
			this.Controls.Add(this.rightTitleLabel);
			this.Controls.Add(this.leftTitleLabel);
			this.Name = "ListSelector";
			this.Size = new System.Drawing.Size(704, 168);
			((System.ComponentModel.ISupportInitialize)(this.rightGrid)).EndInit();
			((System.ComponentModel.ISupportInitialize)(this.leftGrid)).EndInit();
			this.ResumeLayout(false);

		}

		#endregion

		public String LeftTitle
		{
			get { return leftTitleLabel.Text; }
			set { leftTitleLabel.Text = value; }
		}

		public DataContent[] LeftData
		{
			get { return leftItems; }
			set
			{
				leftItems = (DataContent[])new ArrayList(value).ToArray(itemType);
				WFSUtils.FillDataGrid(leftGrid, leftPropertyList, leftItems);
				leftGrid.AllowSorting=false;
				leftGrid.AllowSorting=true;
				WFSUtils.FillDataGrid(rightGrid, rightPropertyList, rightItems);
				//leftGrid.Invalidate();
			}
		}


		public String RightTitle
		{
			get { return rightTitleLabel.Text; }
			set { rightTitleLabel.Text = value; }
		}

		public DataContent[] RightData
		{
			get { return rightItems; }
			set
			{
				rightItems = (DataContent[])new ArrayList(value).ToArray(itemType);
				WFSUtils.FillDataGrid(rightGrid, rightPropertyList, rightItems);
				WFSUtils.FillDataGrid(leftGrid, leftPropertyList, leftItems);
				leftGrid.AllowSorting=false;
				leftGrid.AllowSorting=true;
				//rightGrid.Invalidate();
			}
		}

		private void moveLeftButton_Click(object sender, EventArgs e)
		{
			MoveLeft();
		}
		
		public void MoveLeft()
		{
			MoveLeft(WFSUtils.GetCurrentRowIndex(rightGrid));
		}

		public void MoveLeft(int i)
		{
			if (i >= 0)
			{
				ArrayList a = new ArrayList(leftItems);
				a.Add(rightItems[i]);
				leftItems = (DataContent[]) a.ToArray(itemType);

				a = new ArrayList(rightItems);
				a.RemoveAt(i);
				rightItems = (DataContent[]) a.ToArray(itemType);
				WFSUtils.FillDataGrid(leftGrid, leftPropertyList, leftItems);
				WFSUtils.FillDataGrid(rightGrid, rightPropertyList, rightItems);
				if (i < this.rightItems.Length)
				{
					this.rightGrid.UnSelect(WFSUtils.GetCurrentRowIndex(rightGrid));
					this.rightGrid.Select(i);
					this.rightGrid.CurrentRowIndex = i;
				}
				else if (i > 0)
				{
					this.rightGrid.UnSelect(WFSUtils.GetCurrentRowIndex(rightGrid));
					this.rightGrid.Select(i - 1);
					this.rightGrid.CurrentRowIndex = i - 1;
				}
			}
		}

		private void moveAllLeftButton_Click(object sender, EventArgs e)
		{
			MoveAllLeft();
		}

		public void MoveAllLeft()
		{
			ArrayList a = new ArrayList(leftItems);
			for (int i = 0; i < rightItems.Length; i++)
			{
				a.Add(rightItems[i]);
			}
			leftItems = (DataContent[]) a.ToArray(itemType);
			rightItems = (DataContent[]) Array.CreateInstance(itemType,0);
			WFSUtils.FillDataGrid(leftGrid, leftPropertyList, leftItems);
			WFSUtils.FillDataGrid(rightGrid, rightPropertyList, rightItems);
		}

		public void MoveAllRight()
		{
			ArrayList a = new ArrayList(rightItems);
			for (int i = 0; i < leftItems.Length; i++)
			{
				a.Add(leftItems[i]);
			}
			rightItems = (DataContent[]) a.ToArray(itemType);
			leftItems = (DataContent[]) Array.CreateInstance(itemType,0);
			WFSUtils.FillDataGrid(leftGrid, leftPropertyList, leftItems);
			WFSUtils.FillDataGrid(rightGrid, rightPropertyList, rightItems);
		}

		private void moveAllRightButton_Click(object sender, EventArgs e)
		{
			MoveAllRight();
		}

		private void moveRightButton_Click(object sender, EventArgs e)
		{
			MoveRight();
		}
		
		public void MoveRight()
		{
				MoveRight(WFSUtils.GetCurrentRowIndex(leftGrid));
		}

		private void Grid_MouseUp(object sender, System.Windows.Forms.MouseEventArgs e)
		{
			Point pt = new Point(e.X, e.Y);
			DataGrid.HitTestInfo hti = ((DataGrid)sender).HitTest(pt);
			if (hti.Type == DataGrid.HitTestType.Cell)
			{
				((DataGrid)sender).CurrentCell = new DataGridCell(hti.Row, hti.Column);
				((DataGrid)sender).Select(hti.Row);
			}
		
		}

		public void MoveRight(int i)
		{
			//int i = leftGrid.CurrentRowIndex;
			if (i >= 0)
			{
				ArrayList a = new ArrayList(rightItems);
				a.Add(leftItems[i]);
				rightItems = (DataContent[]) a.ToArray(itemType);

				a = new ArrayList(leftItems);
				a.RemoveAt(i);
				leftItems = (DataContent[]) a.ToArray(itemType);

				WFSUtils.FillDataGrid(leftGrid, leftPropertyList, leftItems);
				WFSUtils.FillDataGrid(rightGrid, rightPropertyList, rightItems);

				if (i < this.leftItems.Length)
				{
					this.leftGrid.UnSelect(WFSUtils.GetCurrentRowIndex(leftGrid));
					this.leftGrid.Select(i);
					this.leftGrid.CurrentRowIndex = i;
				}
				else if (i > 0)
				{
					this.leftGrid.UnSelect(WFSUtils.GetCurrentRowIndex(leftGrid));
					this.leftGrid.Select(i - 1);
					this.leftGrid.CurrentRowIndex = i - 1;
				}
			}
		}

		public PropertyList LeftPropertyList
		{
			get { return leftPropertyList; }
			set
			{
				leftPropertyList = value;
				WFSUtils.FillDataGrid(leftGrid, leftPropertyList, leftItems);
			}
		}
		public PropertyList RightPropertyList
		{
			get { return rightPropertyList; }
			set
			{
				rightPropertyList = value;
				WFSUtils.FillDataGrid(rightGrid, rightPropertyList, rightItems);
			}
		}
		public PropertyList PropertyList
		{
			set
			{
				LeftPropertyList = value;
				RightPropertyList = value;
			}
		}

		public bool RowHeadersVisible
		{
			set
			{
				leftGrid.RowHeadersVisible = value;
				rightGrid.RowHeadersVisible = value;
			}
		}

		public bool ColumnHeadersVisible
		{
			set
			{
				leftGrid.ColumnHeadersVisible = value;
				rightGrid.ColumnHeadersVisible = value;
			}
		}

		public int PreferredColumnWidth
		{
			set
			{
				leftGrid.PreferredColumnWidth = value;
				rightGrid.PreferredColumnWidth = value;
			}
		}

		public int PreferredRowHeight
		{
			set
			{
				leftGrid.PreferredRowHeight = value;
				rightGrid.PreferredRowHeight = value;
			}
		}

		public EventHandler MoveAllLeftButtonEventHandler
		{
			get { return moveAllLeftButtonEventHandler; }
			set
			{
				moveAllLeftButton.Click-=moveAllLeftButtonEventHandler;
				moveAllLeftButtonEventHandler = value;
				moveAllLeftButton.Click+=moveAllLeftButtonEventHandler;
			}
		}

		public EventHandler MoveAllRightButtonEventHandler
		{
			get { return moveAllRightButtonEventHandler; }
			set
			{
				moveAllRightButton.Click-=moveAllRightButtonEventHandler;
				moveAllRightButtonEventHandler = value;
				moveAllRightButton.Click+=moveAllRightButtonEventHandler;
			}
		}

		public EventHandler MoveLeftButtonEventHandler
		{
			get { return moveLeftButtonEventHandler; }
			set
			{
				moveLeftButton.Click-=moveLeftButtonEventHandler;
				moveLeftButtonEventHandler = value;
				moveLeftButton.Click+=moveLeftButtonEventHandler;
			}
		}

		public EventHandler MoveRightButtonEventHandler
		{
			get { return moveRightButtonEventHandler; }
			set
			{
				moveRightButton.Click-=moveRightButtonEventHandler;
				moveRightButtonEventHandler = value;
				moveRightButton.Click+=moveRightButtonEventHandler;
			}
		}

		public Button MoveAllLeftButton
		{
			get { return moveAllLeftButton; }
		}

		public Button MoveAllRightButton
		{
			get { return moveAllRightButton; }
		}

		public Button MoveRightButton
		{
			get { return moveRightButton; }
		}

		public Button MoveLeftButton
		{
			get { return moveLeftButton; }
		}
		
		public int LeftSelectedIndex
		{
			get
			{
				return WFSUtils.GetCurrentRowIndex(leftGrid);
			}
		}

		public DataContent LeftSelected
		{
			get
			{
				
				int i = WFSUtils.GetCurrentRowIndex(leftGrid);
				if(i>=0)
				{
					return LeftData[i];
				}else
				{
					return null;
				}
			}	
		}
		
		public int RightSelectedIndex
		{
			get
			{
				return WFSUtils.GetCurrentRowIndex(rightGrid);
			}
		}

		public DataContent RightSelected
		{
			get
			{
				int i=WFSUtils.GetCurrentRowIndex(rightGrid);
				if(i>=0)
				{
					return RightData[i];
				}
				else
				{
					return null;
				}
			}	
		}

		public void ResetChanges()
		{
			initialLeft=(DataContent[])leftItems.Clone();
			initialRight=(DataContent[])rightItems.Clone();
		}
		
		public DataContent[] MovedLeft()
		{
			ArrayList all=new ArrayList();
			for (int i = 0; i < initialRight.Length; i++)
			{
				DataContent right = initialRight[i];
				for (int j = 0; j < leftItems.Length; j++)
				{
					DataContent left = leftItems[j];
					if(right==left)//reference comparaison
					{
						all.Add(right);
						break;
					}
				}
			}
			return (DataContent[]) all.ToArray(itemType);
		}
		
		public DataContent[] MovedRight()
		{
			ArrayList all=new ArrayList();
			for (int i = 0; i < initialLeft.Length; i++)
			{
				DataContent left = initialLeft[i];
				for (int j = 0; j < rightItems.Length; j++)
				{
					DataContent right = rightItems[j];
					if(right==left)//reference comparaison
					{
						all.Add(right);
						break;
					}
				}
			}
			return (DataContent[]) all.ToArray(itemType);
		}

		public Type ItemType
		{
			get { return itemType; }
			set
			{
				leftItems=(DataContent[])new ArrayList(leftItems).ToArray(itemType);
				rightItems=(DataContent[])new ArrayList(rightItems).ToArray(itemType);
				itemType = value;
			}
		}

	}
}