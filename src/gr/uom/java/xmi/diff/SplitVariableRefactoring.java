package gr.uom.java.xmi.diff;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.refactoringminer.api.Refactoring;
import org.refactoringminer.api.RefactoringType;

import gr.uom.java.xmi.UMLOperation;
import gr.uom.java.xmi.decomposition.AbstractCodeMapping;
import gr.uom.java.xmi.decomposition.VariableDeclaration;

public class SplitVariableRefactoring implements Refactoring {
	private Set<VariableDeclaration> splitVariables;
	private VariableDeclaration oldVariable;
	private UMLOperation operationBefore;
	private UMLOperation operationAfter;
	private Set<AbstractCodeMapping> variableReferences;
	
	public SplitVariableRefactoring(VariableDeclaration oldVariable, Set<VariableDeclaration> splitVariables,
			UMLOperation operationBefore, UMLOperation operationAfter, Set<AbstractCodeMapping> variableReferences) {
		this.splitVariables = splitVariables;
		this.oldVariable = oldVariable;
		this.operationBefore = operationBefore;
		this.operationAfter = operationAfter;
		this.variableReferences = variableReferences;
	}

	public Set<VariableDeclaration> getSplitVariables() {
		return splitVariables;
	}

	public VariableDeclaration getOldVariable() {
		return oldVariable;
	}

	public UMLOperation getOperationBefore() {
		return operationBefore;
	}

	public UMLOperation getOperationAfter() {
		return operationAfter;
	}

	public Set<AbstractCodeMapping> getVariableReferences() {
		return variableReferences;
	}

	private boolean allVariablesAreParameters() {
		for(VariableDeclaration declaration : splitVariables) {
			if(!declaration.isParameter()) {
				return false;
			}
		}
		return oldVariable.isParameter();
	}

	@Override
	public RefactoringType getRefactoringType() {
		if(allVariablesAreParameters())
			return RefactoringType.SPLIT_PARAMETER;
		return RefactoringType.SPLIT_VARIABLE;
	}

	@Override
	public String getName() {
		return this.getRefactoringType().getDisplayName();
	}

	@Override
	public List<String> getInvolvedClassesBeforeRefactoring() {
		List<String> classNames = new ArrayList<String>();
		classNames.add(operationBefore.getClassName());
		return classNames;
	}

	@Override
	public List<String> getInvolvedClassesAfterRefactoring() {
		List<String> classNames = new ArrayList<String>();
		classNames.add(operationAfter.getClassName());
		return classNames;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getName()).append("\t");
		sb.append(oldVariable);
		sb.append(" to ");
		sb.append(splitVariables);
		sb.append(" in method ");
		sb.append(operationAfter);
		sb.append(" in class ").append(operationAfter.getClassName());
		return sb.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((oldVariable == null) ? 0 : oldVariable.hashCode());
		result = prime * result + ((operationAfter == null) ? 0 : operationAfter.hashCode());
		result = prime * result + ((operationBefore == null) ? 0 : operationBefore.hashCode());
		result = prime * result + ((splitVariables == null) ? 0 : splitVariables.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SplitVariableRefactoring other = (SplitVariableRefactoring) obj;
		if (oldVariable == null) {
			if (other.oldVariable != null)
				return false;
		} else if (!oldVariable.equals(other.oldVariable))
			return false;
		if (operationAfter == null) {
			if (other.operationAfter != null)
				return false;
		} else if (!operationAfter.equals(other.operationAfter))
			return false;
		if (operationBefore == null) {
			if (other.operationBefore != null)
				return false;
		} else if (!operationBefore.equals(other.operationBefore))
			return false;
		if (splitVariables == null) {
			if (other.splitVariables != null)
				return false;
		} else if (!splitVariables.equals(other.splitVariables))
			return false;
		return true;
	}

	@Override
	public List<CodeRange> leftSide() {
		List<CodeRange> ranges = new ArrayList<CodeRange>();
		ranges.add(oldVariable.codeRange()
				.setDescription("original variable declaration")
				.setCodeElement(oldVariable.toString()));
		return ranges;
	}

	@Override
	public List<CodeRange> rightSide() {
		List<CodeRange> ranges = new ArrayList<CodeRange>();
		for(VariableDeclaration splitVariable : splitVariables) {
			ranges.add(splitVariable.codeRange()
					.setDescription("split variable declaration")
					.setCodeElement(splitVariable.toString()));
		}
		return ranges;
	}
	public Class<? extends UMLOperation> getClassBeforeRefactoring(){
		System.out.println("clase antes");
		return null;
	}
	public Class<? extends Class> getClassAfterRefactoring(){
		System.out.println("clase despues");
		return null;
	}
}
