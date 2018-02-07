package com.trs.bj.commonstructure.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.transition.ChangeBounds
import android.transition.Scene
import android.transition.TransitionManager
import android.view.View
import android.view.ViewGroup

import com.trs.bj.commonstructure.R


class TransitionAnimationActivity : AppCompatActivity(), View.OnClickListener {
/*在Kotlin编程中有四种修饰词：private,protected,internal,public，
默认public 任何地方可见
protected 在 “top-level” 中不可以使用，即不能修饰包级别的方法或者属性等
private 声明在包含声明的源文件中可见
internal 声明，在同一模块中的任何地方可见*/
    internal var rootView: ViewGroup?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basic_transitions)
        rootView = findViewById(R.id.rootView)


        findViewById<View>(R.id.begin).setOnClickListener(this)
        findViewById<View>(R.id.addtarget).setOnClickListener(this)
        //   findViewById(R.id.delayTransition).setOnClickListener(this);
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.begin -> defaultTransition()
            R.id.addtarget -> addTarget()
        }/*   case R.id.delayTransition:
                delayTransition();
                break;*/
    }

    private fun defaultTransition() {
        val scene2 = Scene.getSceneForLayout(rootView, R.layout.scene2, this)
        /*This transition captures the layout bounds of target views before
        and after the scene change and animates those changes during the transition.

        A ChangeBounds transition can be described in a resource file by using the tag
        changeBounds, along with the other standard attributes of Transition.*/
        val changeBounds = ChangeBounds()
        changeBounds.duration = 500
        TransitionManager.go(scene2, changeBounds)
    }

    private fun addTarget() {
        val scene2 = Scene.getSceneForLayout(rootView, R.layout.scene2, this)

        val changeBounds = ChangeBounds()
        /*Adds the id of a target view that this Transition is interested in animating. By default, there are no targetIds,
        and a Transition will listen for changes on every view in the hierarchy below the sceneRoot of the Scene
        being transitioned into. Setting targetIds constrains the Transition to only listen for, and act on, views with these IDs.
        Views with different IDs, or no IDs whatsoever, will be ignored.Adds the id of a target view that this Transition is
         interested in animating. By default, there are no targetIds, and a Transition will listen for changes on every view
         in the hierarchy below the sceneRoot of the Scene being transitioned into. Setting targetIds constrains the
         Transition to only listen for, and act on, views with these IDs. Views with different IDs, or no IDs whatsoever, will be ignored.*/
        changeBounds.addTarget(R.id.image1)
        TransitionManager.go(scene2, changeBounds)
    }

    companion object {


        private val TAG = "BasicTransitions"
    }

    /*private void delayTransition() {

        Transition transition = new AutoTransition();
        transition.addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {

            }

            @Override
            public void onTransitionEnd(Transition transition) {

                TransitionSet transitionSet = new TransitionSet();
                transitionSet.setDuration(1000);
                ChangeBounds changeBounds = new ChangeBounds();
                changeBounds.setDuration(1000);
                transitionSet.setOrdering(TransitionSet.ORDERING_TOGETHER);
                transitionSet.addTransition(changeBounds);
                Fade fade = new Fade();
                fade.setDuration(1000);
                transitionSet.addTransition(fade);

                TransitionManager.beginDelayedTransition(rootView,transitionSet);
                rootView.findViewById(R.id.contianer).setVisibility(View.VISIBLE);
                ViewGroup.LayoutParams layoutParams = rootView.findViewById(R.id.image1).getLayoutParams();
                layoutParams.height = 800;
                layoutParams.width = 800;
                rootView.findViewById(R.id.image1).setLayoutParams(layoutParams);
            }

            @Override
            public void onTransitionCancel(Transition transition) {

            }

            @Override
            public void onTransitionPause(Transition transition) {

            }

            @Override
            public void onTransitionResume(Transition transition) {

            }
        });
        TransitionManager.beginDelayedTransition(rootView,transition);
        rootView.findViewById(R.id.contianer).setVisibility(View.GONE);
    }*/
}
