#!/bin/sh
xjc -J-Duser.language=en -d src schema/CellDesigner.xsd schema/sbml-level-2-v4-wo-annotation.xsd schema/sbml-mathml.xsd schema/sbmlCellDesignerExtension_v4_2.xsd
