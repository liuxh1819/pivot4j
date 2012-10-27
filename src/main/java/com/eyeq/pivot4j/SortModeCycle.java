/*
 * ====================================================================
 * This software is subject to the terms of the Common Public License
 * Agreement, available at the following URL:
 *   http://www.opensource.org/licenses/cpl.html .
 * You must accept the terms of that agreement to use this software.
 * ====================================================================
 */
package com.eyeq.pivot4j;

public abstract class SortModeCycle {

	public static final SortModeCycle BASIC = new SortModeCycle() {

		@Override
		public SortMode nextMode(SortMode mode) {
			if (mode == null) {
				return SortMode.ASC;
			} else if (mode == SortMode.ASC) {
				return SortMode.DESC;
			} else {
				return null;
			}
		}
	};

	public static final SortModeCycle BREAKING = new SortModeCycle() {

		@Override
		public SortMode nextMode(SortMode mode) {
			if (mode == null) {
				return SortMode.BASC;
			} else if (mode == SortMode.BASC) {
				return SortMode.BDESC;
			} else {
				return null;
			}
		}
	};

	public static final SortModeCycle COUNT = new SortModeCycle() {

		@Override
		public SortMode nextMode(SortMode mode) {
			if (mode == null) {
				return SortMode.TOPCOUNT;
			} else if (mode == SortMode.TOPCOUNT) {
				return SortMode.BOTTOMCOUNT;
			} else {
				return null;
			}
		}
	};

	public abstract SortMode nextMode(SortMode mode);

	/**
	 * @param model
	 */
	public void toggleSort(PivotModel model) {
		if (model == null) {
			throw new IllegalArgumentException(
					"Missing required argument 'model'.");
		}

		SortMode currentMode = null;
		if (model.isSorting()) {
			currentMode = model.getSortMode();
		}

		SortMode mode = nextMode(currentMode);
		if (mode == null) {
			model.setSorting(false);
		} else {
			model.setSorting(true);
			model.setSortMode(mode);
		}
	}
}