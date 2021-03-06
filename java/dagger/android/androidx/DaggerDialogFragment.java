/*
 * Copyright (C) 2018 The Dagger Authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.tangpj.recurve.dagger.androidx;

import android.content.Context;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.internal.Beta;
import javax.inject.Inject;

/**
 * A {@link DialogFragment} that injects its members in {@link #onAttach(Context)} and can be used
 * to inject child {@link Fragment}s attached to it. Note that when this fragment gets reattached,
 * its members will be injected again.
 */
@Beta
public abstract class DaggerDialogFragment extends DialogFragment
    implements HasAndroidxFragmentInjector {

  @Inject DispatchingAndroidInjector<Fragment> childFragmentInjector;

  @Override
  public void onAttach(Context context) {
    AndroidxInjection.inject(this);
    super.onAttach(context);
  }

  @Override
  public AndroidInjector<Fragment> androidxFragmentInjector() {
    return childFragmentInjector;
  }
}
