package app.frontend.screens.subjects;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;
import app.backend.entities.Subjects;
import app.backend.services.SubjectsService;
import app.frontend.components.ActionsButtons;
import app.frontend.components.Table;
import app.frontend.components.TopBar;
import app.frontend.components.Button.ButtonInfo;
import app.frontend.components.Table.CellEditor;
import app.frontend.components.Table.CellRenderer;
import app.frontend.models.SubjectTableModel;
import app.frontend.screens.subjects.forms.SubjectForm;
import app.frontend.screens.subjects.forms.SubjectForm.SubjectActionType;
import res.img.ImagesManager;
import res.values.ColorsManager;
import utils.ComponentDecorator;

/**
 * Classe que representa o gerenciamento de disciplinas.
 */
public class SubjectsManager extends JPanel {

  // Título da janela de gerenciamento de disciplinas
  private static final String TITLE_WINDOW = "Gerenciamento de Disciplinas";

  // Componentes da interface de gerenciamento de disciplinas
  private TopBar topBar;
  private Table table;
  private SubjectTableModel subjectTableModel;
  private int lastSelectedRow = 0;

  /**
   * Construtor da classe SubjectsManager.
   */
  public SubjectsManager() {
    this.setLayout(new BorderLayout(0, 0));

    // Configurar a barra superior (topBar)
    topBar = new TopBar(TITLE_WINDOW);
    topBar.setBackground(ColorsManager.getOnBackgroundColor());
    ComponentDecorator.addBorderBottom(topBar, 1);

    topBar.setActionButton("Cadastrar", ImagesManager.getAddIcon(), new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        new SubjectForm(subjectTableModel, SubjectActionType.ADD_SUBJECT);
      }
    });

    this.add(topBar, BorderLayout.PAGE_START);

    // Criar um contêiner para a tabela de disciplinas
    JPanel contentContainer = new JPanel(new BorderLayout());

    JPanel tableContainer = new JPanel() {
      @Override
      protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        int width = getWidth();
        int height = getHeight();

        // Criar um retângulo com cantos arredondados para o fundo do painel
        RoundRectangle2D roundedRectangle = new RoundRectangle2D.Float(0, 0, width - 1, height - 1, 24, 24);

        g2d.setColor(ColorsManager.getOnBackgroundColor());
        g2d.fill(roundedRectangle);

        // Desenhar a borda do painel
        g2d.setColor(ColorsManager.getOnBackgroundColor()); // Cor da borda personalizável
        g2d.draw(roundedRectangle);
      }
    };

    // Inicializar o modelo da tabela de disciplinas
    subjectTableModel = new SubjectTableModel();
    table = new Table(subjectTableModel);

    // Configurar botões de ação personalizados para a tabela
    ArrayList<ButtonInfo> buttonInfos = new ArrayList<>();
    buttonInfos.add(new ButtonInfo("", ImagesManager.getInfoIcon(), e -> {
      showSubjectInfo();
    }));
    buttonInfos.add(new ButtonInfo("", ImagesManager.getEditIcon(), e -> {
      editSubject();
    }));
    buttonInfos.add(new ButtonInfo("", ImagesManager.getDeleteIcon(), e -> {
      deleteSubject();
    }));

    ActionsButtons actionsButtons = new ActionsButtons(buttonInfos);
    CellEditor actionsButtonCellEditor = new CellEditor(actionsButtons);
    CellRenderer actionsButtonCellRenderer = new CellRenderer(actionsButtons);

    table.setCustomColumn(SubjectTableModel.ACTIONS_BUTTON_COLUMN_INDEX, actionsButtonCellEditor,
        actionsButtonCellRenderer);
    table.setColumnWidth(SubjectTableModel.ACTIONS_BUTTON_COLUMN_INDEX, 120);

    tableContainer.setLayout(new BorderLayout());
    tableContainer.add(table, BorderLayout.CENTER);

    contentContainer.add(tableContainer, BorderLayout.CENTER);
    tableContainer.setBorder(null);

    ComponentDecorator.addPadding(table, 0);
    ComponentDecorator.addPadding(tableContainer, 12);
    ComponentDecorator.addPadding(contentContainer, 24);

    this.add(contentContainer);

    // Obter a lista de disciplinas do serviço e atualizar o modelo da tabela
    ArrayList<Subjects> subjects = SubjectsService.getSubjects();
    if (subjects != null)
      subjectTableModel.setSubjectsList(subjects);
  }

  /**
   * Abre o formulário de edição para uma disciplina selecionada na tabela.
   */
  private void editSubject() {
    int selectedRow = table.getComponent().getSelectedRow();

    if (selectedRow == -1)
      selectedRow = lastSelectedRow;
    else
      lastSelectedRow = selectedRow;

    Subjects subject = subjectTableModel.getSubjectsAt(selectedRow);
    new SubjectForm(subjectTableModel, SubjectActionType.EDIT_SUBJECT, subject);
  }

  /**
   * Abre o formulário de visualização de detalhes para uma disciplina selecionada
   * na tabela.
   */
  private void showSubjectInfo() {
    int selectedRow = table.getComponent().getSelectedRow();

    if (selectedRow == -1)
      selectedRow = lastSelectedRow;
    else
      lastSelectedRow = selectedRow;

    Subjects subject = subjectTableModel.getSubjectsAt(selectedRow);
    new SubjectForm(subjectTableModel, SubjectActionType.INFO_SUBJECT, subject);
  }

  /**
   * Exclui uma disciplina selecionada na tabela.
   */
  private void deleteSubject() {
    int selectedRow = table.getComponent().getSelectedRow();

    if (selectedRow == -1)
      selectedRow = lastSelectedRow;
    else
      lastSelectedRow = selectedRow;

    Subjects subject = subjectTableModel.getSubjectsAt(selectedRow);
    SubjectsService.deleteSubjectById(subject.getId());
    subjectTableModel.setSubjectsList(SubjectsService.getSubjects());
  }
}
