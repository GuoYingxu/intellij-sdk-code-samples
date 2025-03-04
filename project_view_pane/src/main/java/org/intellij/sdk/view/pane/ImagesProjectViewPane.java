// Copyright 2000-2023 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.
package org.intellij.sdk.view.pane;

import com.intellij.icons.AllIcons;
import com.intellij.ide.SelectInTarget;
import com.intellij.ide.impl.ProjectViewSelectInTarget;
import com.intellij.ide.projectView.ViewSettings;
import com.intellij.ide.projectView.impl.*;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

import javax.swing.tree.DefaultTreeModel;

public class ImagesProjectViewPane extends AbstractProjectViewPaneWithAsyncSupport {

  public static final String ID = "IMAGES";

  protected ImagesProjectViewPane(Project project) {
    super(project);
  }

  @NotNull
  @Override
  public String getTitle() {
    return "SDK Images";
  }

  @NotNull
  @Override
  public javax.swing.Icon getIcon() {
    return AllIcons.FileTypes.Custom;
  }

  @NotNull
  @Override
  public String getId() {
    return ID;
  }

  @Override
  public int getWeight() {
    return 10;
  }

  @NotNull
  @Override
  public SelectInTarget createSelectInTarget() {
    return new ProjectViewSelectInTarget(myProject) {

      @Override
      public String toString() {
        return ID;
      }

      @Override
      public String getMinorViewId() {
        return ID;
      }

      @Override
      public float getWeight() {
        return 10;
      }
    };
  }

  @NotNull
  @Override
  protected ProjectAbstractTreeStructureBase createStructure() {
    return new ProjectTreeStructure(myProject, ID) {
      @Override
      protected ImagesProjectNode createRoot(@NotNull Project project, @NotNull ViewSettings settings) {
        return new ImagesProjectNode(project);
      }

      // Children will be searched in async mode
      @Override
      public boolean isToBuildChildrenInBackground(@NotNull Object element) {
        return true;
      }
    };
  }

  @NotNull
  @Override
  protected ProjectViewTree createTree(@NotNull DefaultTreeModel model) {
    return new ProjectViewTree(model) {
      @Override
      public boolean isRootVisible() {
        return true;
      }
    };
  }

}
