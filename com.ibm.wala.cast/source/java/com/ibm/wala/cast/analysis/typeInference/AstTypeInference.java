/******************************************************************************
 * Copyright (c) 2002 - 2006 IBM Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *****************************************************************************/
package com.ibm.wala.cast.analysis.typeInference;

import com.ibm.wala.analysis.typeInference.ConeType;
import com.ibm.wala.analysis.typeInference.TypeInference;
import com.ibm.wala.cast.ir.ssa.AstAssertInstruction;
import com.ibm.wala.cast.ir.ssa.AstGlobalRead;
import com.ibm.wala.cast.ir.ssa.AstGlobalWrite;
import com.ibm.wala.cast.ir.ssa.AstInstructionVisitor;
import com.ibm.wala.cast.ir.ssa.AstLexicalRead;
import com.ibm.wala.cast.ir.ssa.AstLexicalWrite;
import com.ibm.wala.cast.ir.ssa.EachElementGetInstruction;
import com.ibm.wala.cast.ir.ssa.EachElementHasNextInstruction;
import com.ibm.wala.cast.ir.ssa.NonExceptingThrowInstruction;
import com.ibm.wala.ipa.cha.ClassHierarchy;
import com.ibm.wala.ssa.IR;

public class AstTypeInference extends TypeInference {

  protected class AstTypeOperatorFactory
      extends TypeOperatorFactory
      implements AstInstructionVisitor 
  {
    public void visitAstLexicalRead(AstLexicalRead inst) {
      result = new DeclaredTypeOperator(new ConeType(cha.getRootClass(), cha));
    }
    public void visitAstLexicalWrite(AstLexicalWrite inst) {
    }
    public void visitAstGlobalRead(AstGlobalRead instruction) {
      result = new DeclaredTypeOperator(new ConeType(cha.getRootClass(), cha));
    }
    public void visitAstGlobalWrite(AstGlobalWrite instruction) {
    }
    public void visitNonExceptingThrow(NonExceptingThrowInstruction inst) {
    }
    public void visitAssert(AstAssertInstruction instruction) {
    }
    public void visitEachElementGet(EachElementGetInstruction inst) {
      result = new DeclaredTypeOperator(new ConeType(cha.getRootClass(), cha));
    }
    public void visitEachElementHasNext(EachElementHasNextInstruction inst) {
    }
  };

  public AstTypeInference(IR ir, ClassHierarchy cha, boolean doPrimitives) {
    super(ir, cha, doPrimitives);
  }

  protected void initialize() {
    init(ir, new TypeVarFactory(), new AstTypeOperatorFactory());
  }

}

